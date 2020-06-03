package pl.potoczak.cshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.ChosenOffer;
import pl.potoczak.cshoes.model.ClientAgent;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;
import pl.potoczak.cshoes.repository.ChosenOfferRepository;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ClientAgentService {

    private SearchService searchService;
    private List<ShopShoesOffer> offerList;
    private ShoesService shoesService;
    private ChosenOfferRepository chosenOfferRepository;
    private ShoesSearchDto dto;

    @Autowired
    public ClientAgentService(SearchService searchService, ChosenOfferRepository chosenOfferRepository, ShoesService shoesService) {
        this.searchService = searchService;
        this.chosenOfferRepository = chosenOfferRepository;
        this.shoesService = shoesService;
    }

    public void initSearch(ShoesSearchDto shoesSearchDto, List<ShopAgent> shopAgentList, int clientsNumber) throws ExecutionException, InterruptedException {
        dto = shoesSearchDto;
        List<CompletableFuture<ClientAgent>> clientAgents = new ArrayList<>();

        for (int i = 0; i < clientsNumber; i++) {
            CompletableFuture<ClientAgent> clientAgent = searchService.searchShoes(i, shoesSearchDto, new ArrayList<>(shopAgentList));
            clientAgents.add(clientAgent);
        }

        CompletableFuture<List<ClientAgent>> clientAgentsResults = checkClientAgentsEndSearch(clientAgents);
        List<ClientAgent> clientAgentList = clientAgentsResults.get();

        chooseSimilarShoesWithTheBestPrice(clientAgentList);
    }

    private void chooseSimilarShoesWithTheBestPrice(List<ClientAgent> clientAgentList) {
        offerList = new ArrayList<>();
        for(ClientAgent clientAgent : clientAgentList) {
            if (clientAgent.getShoesOffer() != null) {
                boolean isExists = false;
                ShopShoesOffer shopShoesOffer = clientAgent.getShoesOffer();
                ListIterator<ShopShoesOffer> iterator = offerList.listIterator();
                while (iterator.hasNext()) {
                    ShopShoesOffer shoesOffer = iterator.next();
                    if (shopShoesOffer.getShoes().equals(shoesOffer.getShoes())) {
                        isExists = true;
                        if (shopShoesOffer.getPrice().compareTo(shoesOffer.getPrice()) < 0) {
                            iterator.set(shopShoesOffer);
                        }
                    }
                }
                if (!isExists)
                    offerList.add(shopShoesOffer);
            }
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

    public void saveChosenOffer(int chosenShoesId) {
        for(ShopShoesOffer offer : offerList){
            if(offer.getShoes().getId()==chosenShoesId){
                ChosenOffer chosenOffer = new ChosenOffer();
                chosenOffer.setShoes(offer.getShoes());
                chosenOffer.setSize(offer.getSize());
                chosenOffer.setPrice(offer.getPrice());
                chosenOffer.setColor(shoesService.getColorById((long) dto.getColor()));
                chosenOffer.setManufacturer(shoesService.getManufacturerById((long) dto.getManufacturer()));
                chosenOffer.setType(shoesService.getTypeById((long) dto.getCategory()));
                chosenOffer.setGenderGroup(shoesService.getGenderGroupById((long) dto.getWho()));
                chosenOfferRepository.save(chosenOffer);
            }
        }
    }
}
