package pl.potoczak.cshoes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShopAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "shopAgent", cascade = CascadeType.ALL)
    List<ShopShoesOffer> shoesOffers;

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
