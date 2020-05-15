package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.ShopAgent;
import pl.potoczak.cshoes.repository.ManufacturerRepository;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.repository.TypeRepository;
import pl.potoczak.cshoes.service.ShopAgentService;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/shoes")
public class MainController {

    private ShoesRepository shoesRepository;
    private ShopAgentService shopAgentService;

    @Autowired
    public MainController(ShoesRepository shoesRepository, ShopAgentService shopAgentService) {
        this.shoesRepository = shoesRepository;
        this.shopAgentService = shopAgentService;
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
        System.out.println(shoesSearchDto.toString());
        List<ShopAgent> shopAgents = shopAgentService.createAndFillShops(shoesSearchDto.getShopsNumber());
        for(ShopAgent shopAgent : shopAgents){
            System.out.println(shopAgent.getName() + " "+ shopAgent.getShoesOffers().size());
        }
    }

}
