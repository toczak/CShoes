package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.model.parameters.Picture;

import java.util.Optional;

public interface ShopAgentRepository extends CrudRepository<ShopAgent, Long> {
}
