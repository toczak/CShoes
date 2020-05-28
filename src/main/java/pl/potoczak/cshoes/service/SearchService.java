package pl.potoczak.cshoes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.*;
import pl.potoczak.cshoes.model.parameters.SearchParameters;
import pl.potoczak.cshoes.model.parameters_match.ColorMatch;
import pl.potoczak.cshoes.model.parameters_match.GenderGroupMatch;
import pl.potoczak.cshoes.model.parameters_match.ManufacturerMatch;
import pl.potoczak.cshoes.model.parameters_match.TypeMatch;
import pl.potoczak.cshoes.repository.ShopNegotiationRepository;
import pl.potoczak.cshoes.repository.ShopShoesOfferRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAgent.class);

    private ShopShoesOfferRepository offerRepository;
    private ShopNegotiationRepository shopNegotiationRepository;
    private ShoesService shoesService;

    @Autowired
    public SearchService(ShopShoesOfferRepository offerRepository, ShopNegotiationRepository shopNegotiationRepository, ShoesService shoesService) {
        this.offerRepository = offerRepository;
        this.shopNegotiationRepository = shopNegotiationRepository;
        this.shoesService = shoesService;
    }

   /* @Async
    public CompletableFuture<ClientAgent> searchShoes(int i, ShoesSearchDto shoesSearchDto, List<ShopAgent> shopAgentList) throws InterruptedException {
        ClientAgent clientAgent = new ClientAgent();
        if (shopAgentList.size() == 0) {
            clientAgent.setName("emptyShop");
            clientAgent.setShoesOffer(null);
        } else {
            ShopAgent shopAgent = shopAgentList.remove(0);
            if (!shopAgent.isBusy()) {
                shopAgent.setBusy(true);
                clientAgent.setName("client" + i+": ");
                clientAgent.setShoesOffer(getBestShoesOffer(clientAgent.getName(), shopAgent, shoesSearchDto));
                shopAgent.setBusy(false);
            } else {
                shopAgentList.add(shopAgent);
            }
        }
        if (shopAgentList.size() > 0)
            if (clientAgent.getShoesOffer() == null) {
                    //wait 100ms to increase chance that another agent it's not busy
                    //for the time - it's working
                    Thread.sleep(100);
                return searchShoes(i, shoesSearchDto, shopAgentList);
            }

        return CompletableFuture.completedFuture(clientAgent);
    }*/

    @Async
    public CompletableFuture<ClientAgent> searchShoes(int clientI, ShoesSearchDto shoesSearchDto, List<ShopAgent> shopAgentList) throws InterruptedException {
        ClientAgent clientAgent = new ClientAgent();
        clientAgent.setName("client" + clientI + ": ");
        Iterator i = shopAgentList.iterator();
        while (i.hasNext()) {
            ShopAgent shopAgent = (ShopAgent) i.next();
            if (!shopAgent.isBusy()) {
                shopAgent.setBusy(true);
                clientAgent.setShoesOffer(getBestShoesOffer(clientAgent.getName(), shopAgent, shoesSearchDto));
                i.remove();
                shopAgent.setBusy(false);
            }
            if (clientAgent.getShoesOffer() == null) {
//                LOGGER.info(shopAgent.getName() + shopAgent.isBusy() + " = nic nie znalazł");
                Thread.sleep(100);
                if (!i.hasNext() && shopAgentList.size() > 0) {
//                    LOGGER.info(shopAgent.getName() + shopAgent.isBusy() + " = moge wziac kolejny sklep");
                    i = shopAgentList.iterator();
                }
// else
//                    LOGGER.info(shopAgent.getName() + shopAgent.isBusy() + " = nie moge wziac kolejnego sklepu");


            } else break;
        }
        if (clientAgent.getShoesOffer() == null) {
            clientAgent.setName("emptyShop");
        }

        return CompletableFuture.completedFuture(clientAgent);
    }

    private SearchParameters getParametersFromDto(ShoesSearchDto shoesSearchDto) {
        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setCategory(shoesSearchDto.getCategory());
        searchParameters.setColor(shoesSearchDto.getColor());
        searchParameters.setManufacturer(shoesSearchDto.getManufacturer());
        searchParameters.setPriceMin(shoesSearchDto.getPriceMin());
        searchParameters.setSize(shoesSearchDto.getSize());
        searchParameters.setWho(shoesSearchDto.getWho());

        //always search shoes with price 10% above max than user set (to negotiation)
        BigDecimal newPriceMax = shoesSearchDto.getPriceMax().add(shoesSearchDto.getPriceMax().multiply(new BigDecimal((double) 10 / 100)));
        searchParameters.setPriceMax(newPriceMax);

        return searchParameters;
    }

    private ShopShoesOffer getBestShoesOffer(String name, ShopAgent shopAgent, ShoesSearchDto shoesSearchDto) {
        System.out.println("----------------------------");
        System.out.println(shopAgent.getName());

        List<ShopShoesOffer> shoesOffers = searchShoesAtDatabase(shopAgent, shoesSearchDto);
        if (shoesOffers.size() == 0)
            return null;

        calculatePriceDifferenceAndAmountSold(shoesOffers);
        sortOffersWithShopAgentKnowledge(shoesOffers);
        setOffersLevelOfSignificance(shoesOffers, shoesSearchDto);

        //correct with parameters
        //now negotiation and return if negotiation positive
        System.out.println(shopAgent.getName());
        for (ShopShoesOffer shoesOffer : shoesOffers) {
            if (negotiatePrice(shoesOffer, shopAgent, shoesSearchDto.getPriceMin(), shoesSearchDto.getPriceMax())) {
                saveInformationAboutNegotiation(shopAgent, shoesOffer, shoesSearchDto, true);
                LOGGER.info(name + " " + shopAgent.getName() + " " + shoesOffer.getShoes().getName() + " p:" + shoesOffer.getPrice() + " " + shoesOffer.getPurchasePrice() + " a:" + shoesOffer.getAmount() + " p1:" + shoesOffer.getPriceDifference() + " a1:" + shoesOffer.getAmountSold() + " sig:" + shoesOffer.getSignificance());

                return shoesOffer;
            } else {
                saveInformationAboutNegotiation(shopAgent, shoesOffer, shoesSearchDto, false);
//                LOGGER.info(name + " " + shopAgent.getName() + " " + shoesOffer.getShoes().getName() + " p:" + shoesOffer.getPrice() + " " + shoesOffer.getPurchasePrice() + " a:" + shoesOffer.getAmount() + " p1:" + shoesOffer.getPriceDifference() + " a1:" + shoesOffer.getAmountSold() + " sig:" + shoesOffer.getSignificance());

            }
        }
        //return null if negotiation fail
        System.out.println("----------------------------");
        return null;
    }

    private boolean negotiatePrice(ShopShoesOffer shoesOffer, ShopAgent shopAgent, BigDecimal priceMin, BigDecimal priceMax) {
        BigDecimal negotiatePrice = priceMin;
        System.out.println("x"+negotiatePrice);

        //while negotiatePrice is less than priceMax
        while (negotiatePrice.compareTo(priceMax) == -1) {
            boolean isAgree = askShopAgentAboutPrice(shoesOffer, shopAgent, negotiatePrice);
            if (isAgree) {
                shoesOffer.setPrice(negotiatePrice);
                return true;
            } else {
                negotiatePrice = negotiatePrice.add(new BigDecimal(1.00));
                System.out.println(negotiatePrice);

            }
        }
        return false;
    }

    private boolean askShopAgentAboutPrice(ShopShoesOffer shoesOffer, ShopAgent shopAgent, BigDecimal negotiatePrice) {
        //musze uwzglednic tutaj wiedze, czy sie dana oferta sprzedaje czy nie
        BigDecimal expectPrice = getExpectPriceShoesOffer(shoesOffer.getPurchasePrice(), shoesOffer.getPriceDifference(), shoesOffer.getAmountSold(), shopNegotiationRepository.countByShopShoesOffer(shoesOffer));
        LOGGER.info("TTT: " + shoesOffer.getPurchasePrice() + " " + shoesOffer.getAmountSold() + " " +shoesOffer.getPriceDifference()+ " " + shopNegotiationRepository.countByShopShoesOffer(shoesOffer) + " " + expectPrice);
        return expectPrice.compareTo(negotiatePrice) != 1;
    }
/*
    private BigDecimal getExpectPriceShoesOffer(BigDecimal purchasePrice, int amountSold, int valueOfSoldShoesByShop) {
        //zrobić tak, żeby cena zawsze była ustawiona przy negocjacjach, abym nie mmusial jej liczyć przy kazdej negocjacji
        if(valueOfSoldShoesByShop==0) return purchasePrice;

        double percentage = (((1.0 * amountSold) / valueOfSoldShoesByShop) * 100);
        if (percentage > 50) {
            percentage = 10;
        }
        else
            percentage = percentage / 5;
        return purchasePrice.add(purchasePrice.multiply(new BigDecimal(percentage / 100)));
    }*/

    private BigDecimal getExpectPriceShoesOffer(BigDecimal purchasePrice, BigDecimal priceDifference, int amountSold, int valueOfAllShoesNegotiation) {
        //zrobić tak, żeby cena zawsze była ustawiona przy negocjacjach, abym nie mmusial jej liczyć przy kazdej negocjacji
        if(valueOfAllShoesNegotiation==0) return purchasePrice.add(priceDifference);
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

    private void sortOffersWithShopAgentKnowledge(List<ShopShoesOffer> shoesOffers) {
        shoesOffers.sort(Comparator.comparing(ShopShoesOffer::getAmountSold)
                .thenComparing(ShopShoesOffer::getPriceDifference)
                .reversed()
        );
    }

    private void saveInformationAboutNegotiation(ShopAgent shopAgent, ShopShoesOffer shoesOffer, ShoesSearchDto dto, boolean isSold) {
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

    private List<ShopShoesOffer> searchShoesAtDatabase(ShopAgent shopAgent, ShoesSearchDto shoesSearchDto) {
        //add taking into account the knowledge of the selling agent
        return offerRepository.findAllOffersByParameters(shopAgent.getId(), getParametersFromDto(shoesSearchDto));
    }

    private void setOffersLevelOfSignificance(List<ShopShoesOffer> shoesOffers, ShoesSearchDto shoesSearchDto) {
        for (ShopShoesOffer shoesOffer : shoesOffers) {
            Shoes shoes = shoesOffer.getShoes();
            int significance = 0;
            significance += getColorSignificance(shoes.getColorMatchList(), shoesSearchDto);
            significance += getManufacturerSignificance(shoes.getManufacturerMatchList(), shoesSearchDto);
            significance += getTypeSignificance(shoes.getTypeMatchList(), shoesSearchDto);
            significance += getGroupSignificance(shoes.getGenderGroupMatchList(), shoesSearchDto);
            shoesOffer.setSignificance(significance);
        }
//        shoesOffers.sort(Comparator.comparingInt(ShopShoesOffer::getSignificance).reversed());
    }

    private int getGroupSignificance(List<GenderGroupMatch> genderGroupMatchList, ShoesSearchDto shoesSearchDto) {
        for (GenderGroupMatch genderGroupMatch : genderGroupMatchList) {
            if (genderGroupMatch.getGenderGroup().getId() == shoesSearchDto.getWho())
                return genderGroupMatch.getValue() * shoesSearchDto.getWho_important();
        }
        return 0;
    }

    private int getTypeSignificance(List<TypeMatch> typeMatchList, ShoesSearchDto shoesSearchDto) {
        for (TypeMatch typeMatch : typeMatchList) {
            if (typeMatch.getType().getId() == shoesSearchDto.getCategory())
                return typeMatch.getValue() * shoesSearchDto.getCategory_important();
        }
        return 0;
    }

    private int getManufacturerSignificance(List<ManufacturerMatch> manufacturerMatchList, ShoesSearchDto shoesSearchDto) {
        for (ManufacturerMatch manufacturerMatch : manufacturerMatchList) {
            if (manufacturerMatch.getManufacturer().getId() == shoesSearchDto.getManufacturer())
                return manufacturerMatch.getValue() * shoesSearchDto.getManufacturer_important();
        }
        return 0;
    }

    private int getColorSignificance(List<ColorMatch> colorMatchList, ShoesSearchDto shoesSearchDto) {
        for (ColorMatch colorMatch : colorMatchList) {
            if (colorMatch.getColor().getId() == shoesSearchDto.getColor())
                return colorMatch.getValue() * shoesSearchDto.getColor_important();
        }
        return 0;
    }


}
