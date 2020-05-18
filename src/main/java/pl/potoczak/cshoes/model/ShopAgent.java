package pl.potoczak.cshoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShopAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "shopAgent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShopShoesOffer> shoesOffers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopShoesOffer> getShoesOffers() {
        return shoesOffers;
    }

    public void setShoesOffers(List<ShopShoesOffer> shoesOffers) {
        this.shoesOffers = shoesOffers;
    }
}
