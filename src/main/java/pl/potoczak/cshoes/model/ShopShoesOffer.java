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

    private int amount;

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public ShopAgent getShopAgent() {
        return shopAgent;
    }

    public void setShopAgent(ShopAgent shopAgent) {
        this.shopAgent = shopAgent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
