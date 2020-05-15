package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;

public interface ShopShoesOfferRepository extends CrudRepository<ShopShoesOffer, Long> {
}
