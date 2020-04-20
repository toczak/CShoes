package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.cshoes.DTO.ShoesListDTO;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.repository.ShoesRepository;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/shoes")
public class MainController {

    private final ShoesRepository shoesRepository;

    @Autowired
    public MainController(ShoesRepository shoesRepository) {
        this.shoesRepository = shoesRepository;
    }

    @GetMapping(value = "/get/{id}")
    public Shoes getShoes(@PathVariable Long id) {
        return shoesRepository.getById(id);
    }

    @GetMapping(value = "/list")
    public Collection<ShoesListDTO> getListShoes() {
        return shoesRepository.findAllByOrderByPriceAsc();
    }
}
