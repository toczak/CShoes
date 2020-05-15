package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.Manufacturer;

import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
    @Override
    Optional<Manufacturer> findById(Long aLong);

    @Override
    Iterable<Manufacturer> findAll();
}
