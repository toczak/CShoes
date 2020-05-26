package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.ShopShoesOffer;
import pl.potoczak.cshoes.model.parameters.SearchParameters;

import java.util.List;

public interface ShopShoesOfferRepository extends CrudRepository<ShopShoesOffer, Long> {

    @Modifying
    @Query(value = "TRUNCATE table shop_shoes_offer", nativeQuery = true)
    void truncateAllData();

    @Query(value = "SELECT * FROM shop_shoes_offer WHERE shop_agent_id= :agent_id AND size = :#{#params.size} and price between :#{#params.priceMin} AND :#{#params.priceMax}" +
            " AND shoes_id IN (SELECT shoes_id FROM gender_group_match WHERE group_id = :#{#params.who})" +
            " AND shoes_id IN (SELECT shoes_id FROM type_match WHERE type_id= :#{#params.category})" +
            " AND shoes_id IN (SELECT shoes_id FROM manufacturer_match WHERE manufacturer_id = :#{#params.manufacturer})" +
            " AND shoes_id IN (SELECT shoes_id FROM color_match WHERE color_id= :#{#params.color})"
            ,nativeQuery = true)
    List<ShopShoesOffer> findAllOffersByParameters(@Param("agent_id") Long agent_id, @Param("params") SearchParameters params);

}
