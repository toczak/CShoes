package pl.potoczak.cshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.ClientAgent;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ClientAgentService {

    private SearchService searchService;
    private List<ShopShoesOffer> offerList;

    @Autowired
    public ClientAgentService(SearchService searchService) {
        this.searchService = searchService;
    }

    public void initSearch(ShoesSearchDto shoesSearchDto, List<ShopAgent> shopAgentList) throws ExecutionException, InterruptedException {
        List<ShopAgent> shopAgents = new ArrayList<>(shopAgentList);
        List<CompletableFuture<ClientAgent>> clientAgents = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            CompletableFuture<ClientAgent> clientAgent = searchService.searchShoes(shoesSearchDto, shopAgents);
            clientAgents.add(clientAgent);
        }

        CompletableFuture<List<ClientAgent>> clientAgentsResults = checkClientAgentsEndSearch(clientAgents);
        List<ClientAgent> clientAgentList = clientAgentsResults.get();

        chooseSimilarShoesWithTheBestPrice(clientAgentList);
    }

    private void chooseSimilarShoesWithTheBestPrice(List<ClientAgent> clientAgentList) {
        offerList = new ArrayList<>();
        for(ClientAgent clientAgent : clientAgentList){
            boolean isExists = false;
            ShopShoesOffer shopShoesOffer = clientAgent.getShoesOffer();
            ListIterator<ShopShoesOffer> iterator = offerList.listIterator();
            while(iterator.hasNext()){
                ShopShoesOffer shoesOffer = iterator.next();
                if(shopShoesOffer.getShoes().equals(shoesOffer.getShoes())){
                    isExists = true;
                    if (shopShoesOffer.getPrice().compareTo(shoesOffer.getPrice()) < 0) {
                        iterator.set(shopShoesOffer);
                    }
                }
            }
            if(!isExists)
                offerList.add(shopShoesOffer);
        }
    }

    private <T> CompletableFuture<List<T>> checkClientAgentsEndSearch(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
    }

    public List<ShopShoesOffer> getFoundedOffers() {
        return offerList;
    }
}
