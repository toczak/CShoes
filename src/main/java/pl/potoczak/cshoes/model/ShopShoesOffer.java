package pl.potoczak.cshoes.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ShopShoesOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shoes shoes;

    @ManyToOne
    private ShopAgent shopAgent;

    private int size;

    private BigDecimal price;

}
