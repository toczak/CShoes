package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.SizesShoes;
import pl.potoczak.cshoes.model.parameters.Type;

import java.util.Optional;

public interface SizesShoesRepository extends CrudRepository<SizesShoes,Long> {
    @Override
    Optional<SizesShoes> findById(Long aLong);

    @Override
    Iterable<SizesShoes> findAll();
}
