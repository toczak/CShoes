package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.Color;

public interface ColorRepository extends CrudRepository<Color, Long> {
}
