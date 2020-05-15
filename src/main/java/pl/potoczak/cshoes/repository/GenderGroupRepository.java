package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.GenderGroup;
import pl.potoczak.cshoes.model.parameters.Manufacturer;

import java.util.Optional;

public interface GenderGroupRepository extends CrudRepository<GenderGroup, Long> {
    Optional<GenderGroup> findById(Long aLong);
    Iterable<GenderGroup> findAll();
}
