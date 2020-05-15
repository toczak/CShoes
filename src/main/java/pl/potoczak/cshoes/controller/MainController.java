package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.repository.ManufacturerRepository;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.repository.TypeRepository;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/shoes")
public class MainController {

    private ShoesRepository shoesRepository;
    private ManufacturerRepository manufacturerRepository;
    private TypeRepository typeRepository;

    @Autowired
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
    public Collection<Shoes> getListShoes() {
        return shoesRepository.findAllBy();
    }

    @PostMapping("/search")
    public void searchShoes(ShoesSearchDto shoesSearchDto){
//        shopAgentService.
        System.out.println(shoesSearchDto.toString());
    }

}
