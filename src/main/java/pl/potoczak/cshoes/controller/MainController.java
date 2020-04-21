package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.cshoes.DTO.ShoesListDTO;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;
import pl.potoczak.cshoes.repository.ManufacturerRepository;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.repository.TypeRepository;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/shoes")
public class MainController {

    private ShoesRepository shoesRepository;
    private ManufacturerRepository manufacturerRepository;
    private TypeRepository typeRepository;

    public MainController(ShoesRepository shoesRepository, ManufacturerRepository manufacturerRepository, TypeRepository typeRepository) {
        this.shoesRepository = shoesRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping(value = "/get/{id}")
    public Shoes getShoes(@PathVariable Long id) {
        return shoesRepository.getById(id);
    }

    @GetMapping(value = "/list")
    public Collection<ShoesListDTO> getListShoes() {
        return shoesRepository.findAllBy();
    }

    @PostMapping("/list/sort")
    public Collection<ShoesListDTO> sortAndFilterListShoes(@RequestParam int price, @RequestParam Long manufacturer_id, @RequestParam Long type_id) {
        Optional<Type> type = typeRepository.findById(type_id);
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(manufacturer_id);
        if (!type.isPresent() && !manufacturer.isPresent()) {
            switch (price) {
                case 0:
                    return getListShoes();
                case 1:
                    return shoesRepository.findAllByOrderByPriceAsc();
                case 2:
                    return shoesRepository.findAllByOrderByPriceDesc();
            }
        }
        if (!type.isPresent()) {
            switch (price) {
                case 0:
                    return shoesRepository.findByManufacturer(manufacturer.get());
                case 1:
                    return shoesRepository.findByManufacturerOrderByPriceAsc(manufacturer.get());
                case 2:
                    return shoesRepository.findByManufacturerOrderByPriceDesc(manufacturer.get());
            }
        }
        if (!manufacturer.isPresent()) {
            switch (price) {
                case 0:
                    return shoesRepository.findByType(type.get());
                case 1:
                    return shoesRepository.findByTypeOrderByPriceAsc(type.get());
                case 2:
                    return shoesRepository.findByTypeOrderByPriceDesc(type.get());
            }
        }
        if (type.isPresent() && manufacturer.isPresent()) {
            switch (price) {
                case 0:
                    return shoesRepository.findByTypeAndManufacturer(type.get(), manufacturer.get());
                case 1:
                    return shoesRepository.findByTypeAndManufacturerOrderByPriceAsc(type.get(), manufacturer.get());
                case 2:
                    return shoesRepository.findByTypeAndManufacturerOrderByPriceDesc(type.get(), manufacturer.get());
            }
        }

        return null;
    }
}
