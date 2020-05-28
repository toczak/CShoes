package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopNegotiation;
import pl.potoczak.cshoes.model.ShopShoesOffer;

public interface ShopNegotiationRepository extends CrudRepository<ShopNegotiation,Long> {
    int countByShopShoesOfferAndIsSoldTrue(ShopShoesOffer shoesOffer);
    int countByShopShoesOffer(ShopShoesOffer shoesOffer);
    int countByShopAgentAndIsSoldTrue(ShopAgent shopAgent);
}
