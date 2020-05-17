package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.parameters.Picture;

import java.util.Optional;

public interface ShopAgentRepository extends CrudRepository<ShopAgent, Long> {
    @Modifying
    @Query(value = "TRUNCATE table shop_agent CASCADE", nativeQuery = true)
    void truncateAllData();
}
