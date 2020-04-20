package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.DTO.ShoesListDTO;
import pl.potoczak.cshoes.model.Shoes;

import java.util.Collection;

public interface ShoesRepository extends CrudRepository<Shoes, Long> {

//    @Query(value = "SELECT s.id,s.name,s.price,s.type,s.genderGroup,s.size FROM Shoes s")
    Collection<ShoesListDTO> findAllByOrderByPriceAsc();
    Collection<ShoesListDTO> findAllByOrderByPriceDesc();

    Shoes getById(Long id);
}
