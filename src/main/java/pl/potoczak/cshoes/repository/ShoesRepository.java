package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;

import java.util.Collection;

public interface ShoesRepository extends CrudRepository<Shoes, Long> {

    Collection<Shoes> findAllBy();
    Shoes getById(Long id);
}
