package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.Picture;

import java.util.Optional;

public interface PictureRepository extends CrudRepository<Picture, Long> {
    Optional<Picture> findById(Long aLong);
    Iterable<Picture> findAll();
}
