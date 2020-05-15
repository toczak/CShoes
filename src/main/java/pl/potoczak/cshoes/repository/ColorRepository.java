package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.Manufacturer;

import java.util.Optional;

public interface ColorRepository extends CrudRepository<Color, Long> {
    Optional<Color> findById(Long aLong);
    Iterable<Color> findAll();
}
