package pl.potoczak.cshoes.model.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Shoes shoes;

    private int sizeMin;
    private int sizeMax;

    public SizesShoes(Shoes shoes, int sizeMin, int sizeMax) {
        this.shoes = shoes;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
    }

    public SizesShoes() {
    }

    public int getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(int sizeMin) {
        this.sizeMin = sizeMin;
    }

    public int getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(int sizeMax) {
        this.sizeMax = sizeMax;
    }
}
