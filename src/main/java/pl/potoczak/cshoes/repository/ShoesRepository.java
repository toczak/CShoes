package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.Shoes;

public interface ShoesRepository extends CrudRepository<Shoes, Long> {
}
