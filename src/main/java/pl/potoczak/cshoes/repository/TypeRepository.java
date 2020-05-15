package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.parameters.Type;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<Type,Long> {
    @Override
    Optional<Type> findById(Long aLong);

    @Override
    Iterable<Type> findAll();
}
