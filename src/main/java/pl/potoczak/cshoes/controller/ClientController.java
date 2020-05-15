package pl.potoczak.cshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.service.ShoesService;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ShoesService shoesService;

    @GetMapping("/get")
    public Set<Shoes> getShoes() throws ExecutionException, InterruptedException {
//        Set<Shoes> list = new HashSet<>();
//        for (CompletableFuture<Shoes> shoesCompletableFuture : shoesService.getAllShoes()) {
//            Shoes shoes = shoesCompletableFuture.get();
//            list.add(shoes);
//        }
//        return list;
        System.out.println("ROZPOCZYNAM WYSZUKIWANIE");
        CompletableFuture<Shoes> shoes1 = shoesService.getShoes(shoesService.random());
        CompletableFuture<Shoes> shoes2 = shoesService.getShoes(shoesService.random());
        CompletableFuture<Shoes> shoes3 = shoesService.getShoes(shoesService.random());
        CompletableFuture.allOf(shoes1,shoes2, shoes3).join();
        Set<Shoes> list = new HashSet<>();
        try {
            list.add(shoes1.get());
            list.add(shoes2.get());
            list.add(shoes3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return list;
    }
}
