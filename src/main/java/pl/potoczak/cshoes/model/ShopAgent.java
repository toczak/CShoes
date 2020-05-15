package pl.potoczak.cshoes.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShopAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "shopAgent")
    List<ShopShoesOffer> shoesOffers;

}
