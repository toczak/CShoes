package pl.potoczak.cshoes.model.parameters;

import org.springframework.scheduling.annotation.EnableAsync;
import pl.potoczak.cshoes.model.Shoes;

import javax.persistence.*;

@Entity
public class SizesShoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private Shoes shoes;

    private int sizeMin;
    private int sizeMax;

    public SizesShoes(Shoes shoes, int sizeMin, int sizeMax) {
        this.shoes = shoes;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
    }
}
