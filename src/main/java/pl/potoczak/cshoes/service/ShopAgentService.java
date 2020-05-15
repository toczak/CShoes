package pl.potoczak.cshoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.repository.ShopAgentRepository;
import pl.potoczak.cshoes.repository.ShopShoesOfferRepository;

@Service
public class ShopAgentService {
    private ShoesRepository shoesRepository;
    private ShopShoesOfferRepository offerRepository;
    private ShopAgentRepository agentRepository;

    @Autowired
    public ShopAgentService(ShoesRepository shoesRepository, ShopShoesOfferRepository offerRepository, ShopAgentRepository agentRepository) {
        this.shoesRepository = shoesRepository;
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
    }


}
