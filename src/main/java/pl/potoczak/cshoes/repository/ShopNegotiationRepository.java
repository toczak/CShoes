package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ShopNegotiation;
import pl.potoczak.cshoes.model.ShopShoesOffer;

public interface ShopNegotiationRepository extends CrudRepository<ShopNegotiation,Long> {
    int countByShopShoesOffer(ShopShoesOffer shoesOffer);
}
