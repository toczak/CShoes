package pl.potoczak.cshoes.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.ClientAgent;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;
import pl.potoczak.cshoes.model.parameters_match.ColorMatch;
import pl.potoczak.cshoes.model.parameters_match.GenderGroupMatch;
import pl.potoczak.cshoes.model.parameters_match.ManufacturerMatch;
import pl.potoczak.cshoes.model.parameters_match.TypeMatch;
import pl.potoczak.cshoes.repository.ShopShoesOfferRepository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAgent.class);

    private ShopShoesOfferRepository offerRepository;

    @Autowired
    public SearchService(ShopShoesOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Async
    public CompletableFuture<ClientAgent> searchShoes(ShoesSearchDto shoesSearchDto, List<ShopAgent> shopAgentList) throws InterruptedException {
        ClientAgent clientAgent = new ClientAgent();
        synchronized (shopAgentList) {
            if (shopAgentList.size() == 0) {
                clientAgent.setName("emptyShop");
                clientAgent.setShoesOffer(null);
            } else {
                ShopAgent shopAgent = shopAgentList.remove(0);
                clientAgent.setName("client: " + shopAgent.getName());
                clientAgent.setShoesOffer(search(shopAgent, shoesSearchDto));
            }
        }
        if (shopAgentList.size() > 0)
            if (clientAgent.getShoesOffer() == null)
                return searchShoes(shoesSearchDto, shopAgentList);

        return CompletableFuture.completedFuture(clientAgent);
    }

    private ShopShoesOffer search(ShopAgent shopAgent, ShoesSearchDto shoesSearchDto) {
        System.out.println("----------------------------");
        System.out.println(shopAgent.getName());

        List<ShopShoesOffer> shoesOffers = offerRepository.findAllOffersByParameters(shopAgent.getId(), shoesSearchDto);
        if (shoesOffers.size() == 0)
            return null;
        sortOffersByLevelOfSignificance(shoesOffers, shoesSearchDto);

        //correct with parameters
        for (ShopShoesOffer shoesOffer : shoesOffers) {
            System.out.println(shoesOffer.getShoes().getName() + " " + shoesOffer.getPrice() + " sig:" + shoesOffer.getSignificance());
        }
        //return first from shop offers
        System.out.println("----------------------------");
        return shoesOffers.get(0);
    }

    private void sortOffersByLevelOfSignificance(List<ShopShoesOffer> shoesOffers, ShoesSearchDto shoesSearchDto) {
        for(ShopShoesOffer shoesOffer : shoesOffers){
            Shoes shoes = shoesOffer.getShoes();
            int significance = 0;
            significance += getColorSignificance(shoes.getColorMatchList(), shoesSearchDto);
            significance += getManufacturerSignificance(shoes.getManufacturerMatchList(), shoesSearchDto);
            significance += getTypeSignificance(shoes.getTypeMatchList(), shoesSearchDto);
            significance += getGroupSignificance(shoes.getGenderGroupMatchList(), shoesSearchDto);
            shoesOffer.setSignificance(significance);
        }
        shoesOffers.sort(Comparator.comparingInt(ShopShoesOffer::getSignificance).reversed());
    }

    private int getGroupSignificance(List<GenderGroupMatch> genderGroupMatchList, ShoesSearchDto shoesSearchDto) {
        for(GenderGroupMatch genderGroupMatch : genderGroupMatchList){
            if(genderGroupMatch.getGenderGroup().getId()==shoesSearchDto.getWho())
                return genderGroupMatch.getValue()*shoesSearchDto.getWho_important();
        }
        return 0;
    }

    private int getTypeSignificance(List<TypeMatch> typeMatchList, ShoesSearchDto shoesSearchDto) {
        for(TypeMatch typeMatch : typeMatchList){
            if(typeMatch.getType().getId()==shoesSearchDto.getCategory())
                return typeMatch.getValue()*shoesSearchDto.getCategory_important();
        }
        return 0;
    }

    private int getManufacturerSignificance(List<ManufacturerMatch> manufacturerMatchList, ShoesSearchDto shoesSearchDto) {
        for(ManufacturerMatch manufacturerMatch : manufacturerMatchList){
            if(manufacturerMatch.getManufacturer().getId()==shoesSearchDto.getManufacturer())
                return manufacturerMatch.getValue()*shoesSearchDto.getManufacturer_important();
        }
        return 0;
    }

    private int getColorSignificance(List<ColorMatch> colorMatchList, ShoesSearchDto shoesSearchDto) {
        for(ColorMatch colorMatch : colorMatchList){
            if(colorMatch.getColor().getId()==shoesSearchDto.getColor())
                return colorMatch.getValue()*shoesSearchDto.getColor_important();
        }
        return 0;
    }




}
