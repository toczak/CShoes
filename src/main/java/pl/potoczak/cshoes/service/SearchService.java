package pl.potoczak.cshoes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.ClientAgent;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAgent.class);

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
                shopAgentList.remove(shopAgent);
                clientAgent.setShoesOffer(search(shopAgent.getShoesOffers()));
                LOGGER.info(clientAgent.getName());
                if (clientAgent.getShoesOffer() != null)
                    LOGGER.info(clientAgent.getName() + " " + clientAgent.getShoesOffer().getShoes().getName());
            }
        }
//        Thread.sleep(3000);
        LOGGER.info("Po " + shopAgentList.size());
        if (shopAgentList.size() > 0)
            if (clientAgent.getShoesOffer() == null)
                return searchShoes(shoesSearchDto, shopAgentList);

        return CompletableFuture.completedFuture(clientAgent);
    }

    private ShopShoesOffer search(List<ShopShoesOffer> shoesOffers) {
        return shoesOffers.stream()
                .filter(s -> s.getSize() == 35)
                .findAny()
                .orElse(null);
    }


}
