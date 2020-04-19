package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.repository.ColorRepository;
import pl.potoczak.cshoes.repository.ShoesRepository;

import java.util.Collection;

@RestController
public class MainController {

    private final ShoesRepository shoesRepository;

    @Autowired
    public MainController(ShoesRepository shoesRepository) {
        this.shoesRepository = shoesRepository;
    }

    @GetMapping(value = "/index")
    public Collection<Shoes> index(){
        return shoesRepository.findAll();
    }
}
