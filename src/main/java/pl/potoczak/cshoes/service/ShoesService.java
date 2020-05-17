package pl.potoczak.cshoes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.repository.ShoesRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class ShoesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoesService.class);

    private ShoesRepository shoesRepository;

    @Autowired
    public ShoesService(ShoesRepository shoesRepository) {
        this.shoesRepository = shoesRepository;
    }


    @Async
    public CompletableFuture<Shoes> getShoes(int id) {
        LOGGER.info("Getting shoes... Id:" + id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("AFTER.");


        final Shoes shoes = shoesRepository.getById((long) id);
        return CompletableFuture.completedFuture(shoes);
    }

    @Async
    public void  getAllShoes() {
        CompletableFuture<Shoes> shoes1 = getShoes(random());
        CompletableFuture<Shoes> shoes2 = getShoes(random());
        CompletableFuture<Shoes> shoes3 = getShoes(random());
        CompletableFuture.allOf(shoes1, shoes2, shoes3).join();
    }

    public int random() {
        return (int) ((Math.random() * 5) + 1);
    }
}
