package pl.potoczak.cshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.repository.ShopAgentRepository;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ShopAgentService {
    private ShoesRepository shoesRepository;
    private ShopAgentRepository agentRepository;
    private List<ShopAgent> shopAgents;

    @Autowired
    public ShopAgentService(ShoesRepository shoesRepository, ShopAgentRepository agentRepository) {
        this.shoesRepository = shoesRepository;
        this.agentRepository = agentRepository;
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

}
