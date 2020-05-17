package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;

public interface ShopShoesOfferRepository extends CrudRepository<ShopShoesOffer, Long> {

    @Modifying
    @Query(value = "TRUNCATE table shop_shoes_offer", nativeQuery = true)
    void truncateAllData();
}
