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
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAgent.class);

    private ShopAgentService shopAgentService;
    private ShoesSearchDto shoesSearchDto;

    @Autowired
    public SearchService(ShopNegotiationRepository shopNegotiationRepository, ShoesService shoesService, ShopAgentService shopAgentService) {
        this.shopAgentService = shopAgentService;
    }

    @Async
    public CompletableFuture<ClientAgent> searchShoes(int clientI, ShoesSearchDto shoesSearchDto, List<ShopAgent> shopAgentList) throws InterruptedException {
        ClientAgent clientAgent = new ClientAgent();
        clientAgent.setName("client" + clientI + ": ");
        Iterator i = shopAgentList.iterator();
        while (i.hasNext()) {
            ShopAgent shopAgent = (ShopAgent) i.next();
            if (!shopAgent.isBusy()) {
                shopAgent.setBusy(true);
                this.shoesSearchDto = shoesSearchDto;
                searchAndChooseBestShoesOffer(clientAgent, shopAgent, shoesSearchDto);
                i.remove();
                shopAgent.setBusy(false);
            }
            if (clientAgent.getShoesOffer() == null) {
                Thread.sleep(100);
                if (!i.hasNext() && shopAgentList.size() > 0) {
                    i = shopAgentList.iterator();
                }
            } else break;
        }
        if (clientAgent.getShoesOffer() == null) {
            clientAgent.setName("emptyShop");
        }

        return CompletableFuture.completedFuture(clientAgent);
    }

    private void searchAndChooseBestShoesOffer(ClientAgent clientAgent, ShopAgent shopAgent, ShoesSearchDto shoesSearchDto) {
        clientAgent.setShoesOffer(shopAgentService.getShoesOffers(clientAgent, shopAgent, getParametersFromDto(shoesSearchDto)));
    }

    public boolean showProductAndNegotiate(ShopAgent shopAgent, ShopShoesOffer shoesOffer) {
        if (!checkSignificance(shoesOffer, shoesSearchDto)) {
            return false;
        }


        if (negotiatePrice(shoesOffer, shopAgent, shoesSearchDto.getPriceMin(), shoesSearchDto.getPriceMax())) {
            informShopAboutNegotiation(shopAgent, shoesOffer, shoesSearchDto, true);
            return true;
        } else {
            informShopAboutNegotiation(shopAgent, shoesOffer, shoesSearchDto, false);
            return false;
        }
    }


    private void informShopAboutNegotiation(ShopAgent shopAgent, ShopShoesOffer shoesOffer, ShoesSearchDto shoesSearchDto, boolean isSold) {
        shopAgentService.saveInformationAboutNegotiation(shopAgent, shoesOffer, getParametersFromDto(shoesSearchDto), isSold);
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


    private boolean negotiatePrice(ShopShoesOffer shoesOffer, ShopAgent shopAgent, BigDecimal priceMin, BigDecimal priceMax) {
        BigDecimal negotiatePrice = priceMin;

        //while negotiatePrice is less than priceMax
        while (negotiatePrice.compareTo(priceMax) <= 0) {
            boolean isAgree = askShopAgentAboutPrice(shoesOffer, shopAgent, negotiatePrice);
            if (isAgree) {
                shoesOffer.setPrice(negotiatePrice);
                return true;
            } else {
                negotiatePrice = negotiatePrice.add(new BigDecimal(1.00));
            }
        }
        return false;
    }

    private boolean askShopAgentAboutPrice(ShopShoesOffer shoesOffer, ShopAgent shopAgent, BigDecimal negotiatePrice) {
        return shopAgentService.thinkAboutOfferedPriceFromClientAgent(shoesOffer, shopAgent, negotiatePrice);
    }

    private boolean checkSignificance(ShopShoesOffer shoesOffer, ShoesSearchDto shoesSearchDto) {
        Shoes shoes = shoesOffer.getShoes();

        if (getColorSignificance(shoes.getColorMatchList(), shoesSearchDto) < shoesSearchDto.getColor_important())
            return false;
        if (getTypeSignificance(shoes.getTypeMatchList(), shoesSearchDto) < shoesSearchDto.getCategory_important())
            return false;
        if (getManufacturerSignificance(shoes.getManufacturerMatchList(), shoesSearchDto) < shoesSearchDto.getManufacturer_important())
            return false;
        if (getGroupSignificance(shoes.getGenderGroupMatchList(), shoesSearchDto) < shoesSearchDto.getWho_important())
            return false;

        return true;
    }

    private int getGroupSignificance(List<GenderGroupMatch> genderGroupMatchList, ShoesSearchDto shoesSearchDto) {
        for (GenderGroupMatch genderGroupMatch : genderGroupMatchList) {
            if (genderGroupMatch.getGenderGroup().getId() == shoesSearchDto.getWho())
                return genderGroupMatch.getValue();
        }
        return 0;
    }

    private int getTypeSignificance(List<TypeMatch> typeMatchList, ShoesSearchDto shoesSearchDto) {
        for (TypeMatch typeMatch : typeMatchList) {
            if (typeMatch.getType().getId() == shoesSearchDto.getCategory())
                return typeMatch.getValue();
        }
        return 0;
    }

    private int getManufacturerSignificance(List<ManufacturerMatch> manufacturerMatchList, ShoesSearchDto shoesSearchDto) {
        for (ManufacturerMatch manufacturerMatch : manufacturerMatchList) {
            if (manufacturerMatch.getManufacturer().getId() == shoesSearchDto.getManufacturer())
                return manufacturerMatch.getValue();
        }
        return 0;
    }

    private int getColorSignificance(List<ColorMatch> colorMatchList, ShoesSearchDto shoesSearchDto) {
        for (ColorMatch colorMatch : colorMatchList) {
            if (colorMatch.getColor().getId() == shoesSearchDto.getColor())
                return colorMatch.getValue();
        }
        return 0;
    }

}
