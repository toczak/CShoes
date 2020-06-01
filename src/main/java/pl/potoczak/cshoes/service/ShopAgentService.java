package pl.potoczak.cshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.*;
import pl.potoczak.cshoes.model.parameters.SearchParameters;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.repository.ShopAgentRepository;
import pl.potoczak.cshoes.repository.ShopNegotiationRepository;
import pl.potoczak.cshoes.repository.ShopShoesOfferRepository;

import java.math.BigDecimal;
import java.util.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@Service
@Transactional
public class ShopAgentService {

    private ShoesRepository shoesRepository;
    private ShopAgentRepository agentRepository;
    private List<ShopAgent> shopAgents;
    private ShopShoesOfferRepository offerRepository;
    private ShopNegotiationRepository shopNegotiationRepository;
    private ShoesService shoesService;
    private SearchService searchService;

    @Autowired
    public ShopAgentService(ShoesRepository shoesRepository, ShopAgentRepository agentRepository, ShopShoesOfferRepository offerRepository, ShopNegotiationRepository shopNegotiationRepository, ShoesService shoesService, @Lazy SearchService searchService) {
        this.shoesRepository = shoesRepository;
        this.agentRepository = agentRepository;
        this.offerRepository = offerRepository;
        this.shopNegotiationRepository = shopNegotiationRepository;
        this.shoesService = shoesService;
        this.searchService = searchService;
    }

    public void createAndFillShops(int shopNumbers) {
        agentRepository.truncateAllData();
        shopAgents = new ArrayList<>();
        List<Shoes> shoesIterable = (List<Shoes>) shoesRepository.findAll();
        for (int i = 0; i < shopNumbers; i++) {
            ShopAgent shopAgent = new ShopAgent();
            shopAgent.setName("Shop" + (i + 1));
            shopAgent.setBusy(false);
            List<Shoes> shoesCopyList = new ArrayList<>(shoesIterable);
            List<ShopShoesOffer> shoesOffers = fillShopShoesOffers(shopAgent, shoesCopyList);
            shopAgent.setShoesOffers(shoesOffers);
            agentRepository.save(shopAgent);
            shopAgents.add(shopAgent);
        }
    }

    private List<ShopShoesOffer> fillShopShoesOffers(ShopAgent shopAgent, List<Shoes> shoesList) {
        Set<Shoes> shoesSet = new HashSet<>();
        Random random = new Random();
        while (Math.round(shoesSet.size() * 100.0 / (shoesSet.size() + shoesList.size())) < 75) {
            Shoes shoes = shoesList.get(random.nextInt(shoesList.size()));
            shoesSet.add(shoes);
            shoesList.remove(shoes);
        }

        List<ShopShoesOffer> offersList = new ArrayList<>();
        for (Shoes shoes : shoesSet) {
            BigDecimal purchasePrice = getPurchasePrice(shoes.getPrice(), random);
            BigDecimal sellPrice = getSellPrice(purchasePrice, random);
            for (int i = shoes.getSizesShoes().getSizeMin(); i <= shoes.getSizesShoes().getSizeMax(); i++) {
                ShopShoesOffer shopShoesOffer = new ShopShoesOffer();
                shopShoesOffer.setShopAgent(shopAgent);
                shopShoesOffer.setShoes(shoes);
                shopShoesOffer.setPurchasePrice(purchasePrice);
                shopShoesOffer.setPrice(sellPrice);
                shopShoesOffer.setSize(i);
                shopShoesOffer.setAmount(random.nextInt(5) + 1);
                offersList.add(shopShoesOffer);
            }
        }
        return offersList;
    }

    private BigDecimal getPurchasePrice(BigDecimal price, Random random) {
        int percentage = random.nextInt(10);
        boolean isMinus = random.nextBoolean();
        BigDecimal newPrice;
        if (isMinus)
            newPrice = price.subtract(price.multiply(new BigDecimal((double) percentage / 100)));
        else
            newPrice = price.add(price.multiply(new BigDecimal((double) percentage / 100)));
        return newPrice;
    }

    private BigDecimal getSellPrice(BigDecimal purchasePrice, Random random) {
        int percentage = random.nextInt(5) + 5;
        BigDecimal newPrice;
        newPrice = purchasePrice.add(purchasePrice.multiply(new BigDecimal((double) percentage / 100)));
        return newPrice;
    }

    public List<ShopAgent> getShopAgents() {
        return shopAgents;
    }

    private List<ShopShoesOffer> searchShoesAtDatabase(ShopAgent shopAgent, SearchParameters searchParameters) {
        //add taking into account the knowledge of the selling agent
        return offerRepository.findAllOffersByParameters(shopAgent.getId(), searchParameters);
    }

    public ShopShoesOffer getShoesOffers(ClientAgent clientAgent, ShopAgent shopAgent, SearchParameters searchParameters) {
        List<ShopShoesOffer> shoesOffers = searchShoesAtDatabase(shopAgent, searchParameters);
        if (shoesOffers.size() == 0)
            return null;

        calculatePriceDifferenceAndAmountSold(shoesOffers);
        sortOffersWithUseKnowledge(shoesOffers);

        for (ShopShoesOffer shoesOffer : shoesOffers) {
            if (showProductAndNegotiationSuccess(shopAgent, shoesOffer)) {
                return shoesOffer;
            }
        }

        //return null if negotiation fail
        return null;
    }

    private boolean showProductAndNegotiationSuccess(ShopAgent shopAgent, ShopShoesOffer shoesOffer) {
        return searchService.showProductAndNegotiate(shopAgent, shoesOffer);
    }

    public boolean thinkAboutOfferedPriceFromClientAgent(ShopShoesOffer shoesOffer, ShopAgent shopAgent, BigDecimal negotiatePrice){
        BigDecimal expectPrice = getExpectPriceShoesOffer(shoesOffer.getPurchasePrice(), shoesOffer.getPriceDifference(), shoesOffer.getAmountSold(), shopNegotiationRepository.countByShopShoesOffer(shoesOffer));
        LOGGER.info("Negotiation "+shopAgent.getName()+": PurchasePrice:" + shoesOffer.getPurchasePrice() + " AmountSold:" + shoesOffer.getAmountSold() + " PriceDifference:" + shoesOffer.getPriceDifference() + " CountNegotiation:" + shopNegotiationRepository.countByShopShoesOffer(shoesOffer) + " ExpectPrice:" + expectPrice + " Negot:"+negotiatePrice);
        return expectPrice.compareTo(negotiatePrice) != 1;
    }

    public BigDecimal getExpectPriceShoesOffer(BigDecimal purchasePrice, BigDecimal priceDifference, int amountSold, int valueOfAllShoesNegotiation) {
        //zrobić tak, żeby cena zawsze była ustawiona przy negocjacjach, abym nie mmusial jej liczyć przy kazdej negocjacji
        if (valueOfAllShoesNegotiation == 0) return purchasePrice.add(priceDifference);
        double percentage = (((1.0 * amountSold) / valueOfAllShoesNegotiation) * 100);
        return purchasePrice.add(priceDifference.multiply(new BigDecimal(percentage / 100)));
    }

    private void calculatePriceDifferenceAndAmountSold(List<ShopShoesOffer> shoesOffers) {
        for (ShopShoesOffer shoesOffer : shoesOffers) {
            BigDecimal priceDifference = shoesOffer.getPrice().subtract(shoesOffer.getPurchasePrice());
            shoesOffer.setPriceDifference(priceDifference);
            shoesOffer.setAmountSold(shopNegotiationRepository.countByShopShoesOfferAndIsSoldTrue(shoesOffer));
        }
    }

    private void sortOffersWithUseKnowledge(List<ShopShoesOffer> shoesOffers) {
        shoesOffers.sort(Comparator.comparing(ShopShoesOffer::getAmountSold)
                .thenComparing(ShopShoesOffer::getPriceDifference)
                .reversed()
        );
    }

    public void saveInformationAboutNegotiation(ShopAgent shopAgent, ShopShoesOffer shoesOffer, SearchParameters dto, boolean isSold) {
        ShopNegotiation shopNegotiation = new ShopNegotiation();
        shopNegotiation.setShopShoesOffer(shoesOffer);
        shopNegotiation.setShopAgent(shopAgent);
        shopNegotiation.setColor(shoesService.getColorById((long) dto.getColor()));
        shopNegotiation.setManufacturer(shoesService.getManufacturerById((long) dto.getManufacturer()));
        shopNegotiation.setType(shoesService.getTypeById((long) dto.getCategory()));
        shopNegotiation.setGenderGroup(shoesService.getGenderGroupById((long) dto.getWho()));
        shopNegotiation.setSold(isSold);
        shopNegotiation.setPrice(shoesOffer.getPrice());
        shopNegotiationRepository.save(shopNegotiation);
    }

}
