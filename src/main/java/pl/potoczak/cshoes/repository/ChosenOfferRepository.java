package pl.potoczak.cshoes.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.model.ChosenOffer;

public interface ChosenOfferRepository extends CrudRepository<ChosenOffer,Long> {
}
