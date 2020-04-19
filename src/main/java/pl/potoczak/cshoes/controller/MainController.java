package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.repository.ColorRepository;
import pl.potoczak.cshoes.repository.ShoesRepository;

@RestController
public class MainController {

    private final ShoesRepository shoesRepository;

    private final ColorRepository colorRepository;

    @Autowired
    public MainController(ShoesRepository shoesRepository, ColorRepository colorRepository) {
        this.shoesRepository = shoesRepository;
        this.colorRepository = colorRepository;
    }

    @GetMapping("/index")
    public String index(){
        Shoes shoes = new Shoes();
        Color color = new Color("white");
        colorRepository.save(color);
        shoes.setColor(color);
        shoesRepository.save(shoes);
        return "No dizeń dobry";
    }
}
