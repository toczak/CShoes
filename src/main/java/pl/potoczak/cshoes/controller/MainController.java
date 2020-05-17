package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.cshoes.dto.ShoesSearchDto;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.ShopShoesOffer;
import pl.potoczak.cshoes.repository.ShoesRepository;
import pl.potoczak.cshoes.service.ClientAgentService;
import pl.potoczak.cshoes.service.ShopAgentService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/shoes")
public class MainController {

    private ShoesRepository shoesRepository;
    private ShopAgentService shopAgentService;
    private ClientAgentService clientAgentService;

    @Autowired
    public MainController(ShoesRepository shoesRepository, ShopAgentService shopAgentService, ClientAgentService clientAgentService) {
        this.shoesRepository = shoesRepository;
        this.shopAgentService = shopAgentService;
        this.clientAgentService = clientAgentService;
    }

    @GetMapping(value = "/get/{id}")
    public Shoes getShoes(@PathVariable Long id) {
        return shoesRepository.getById(id);
    }

    @GetMapping(value = "/list")
    public Collection<Shoes> getListShoes() {
        return shoesRepository.findAllBy();
    }

    @PostMapping("/set-init")
    public ResponseEntity setInit(@RequestParam("shopsNumber") int shopsNumber) {
        shopAgentService.createAndFillShops(shopsNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchShoes(@Valid ShoesSearchDto shoesSearchDto) throws ExecutionException, InterruptedException {
        clientAgentService.initSearch(shoesSearchDto, shopAgentService.getShopAgents());
        return new ResponseEntity<>(clientAgentService.getFoundedOffers(), HttpStatus.OK);

    }

}
