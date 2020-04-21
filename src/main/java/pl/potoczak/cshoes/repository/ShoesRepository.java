package pl.potoczak.cshoes.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potoczak.cshoes.DTO.ShoesListDTO;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;

import java.util.Collection;

public interface ShoesRepository extends CrudRepository<Shoes, Long> {

//    @Query(value = "SELECT s.id,s.name,s.price,s.type,s.genderGroup,s.size FROM Shoes s")
    Collection<ShoesListDTO> findAllBy();
    Collection<ShoesListDTO> findAllByOrderByPriceAsc();
    Collection<ShoesListDTO> findAllByOrderByPriceDesc();

    Collection<ShoesListDTO> findByType(Type type);
    Collection<ShoesListDTO> findByTypeOrderByPriceAsc(Type type);
    Collection<ShoesListDTO> findByTypeOrderByPriceDesc(Type type);

    Collection<ShoesListDTO> findByManufacturer(Manufacturer manufacturer);
    Collection<ShoesListDTO> findByManufacturerOrderByPriceAsc(Manufacturer manufacturer);
    Collection<ShoesListDTO> findByManufacturerOrderByPriceDesc(Manufacturer manufacturer);

    Collection<ShoesListDTO> findByTypeAndManufacturer(Type type, Manufacturer manufacturer);
    Collection<ShoesListDTO> findByTypeAndManufacturerOrderByPriceAsc(Type type, Manufacturer manufacturer);
    Collection<ShoesListDTO> findByTypeAndManufacturerOrderByPriceDesc(Type type, Manufacturer manufacturer);

    Shoes getById(Long id);
}
