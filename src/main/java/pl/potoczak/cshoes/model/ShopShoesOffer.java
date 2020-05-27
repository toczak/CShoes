package pl.potoczak.cshoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ShopShoesOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shoes shoes;

    @ManyToOne
    private ShopAgent shopAgent;

    @OneToMany(mappedBy = "shopShoesOffer")
    @JsonIgnore
    private List<ShopNegotiation> shopNegotiations;

    private int size;

    private BigDecimal price;

    @JsonIgnore
    private BigDecimal purchasePrice;

    private int amount;

    @Transient
    private int significance;

    @Transient
    private BigDecimal priceDifference;

    @Transient
    private int amountSold;

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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSignificance() {
        return significance;
    }

    public void setSignificance(int significance) {
        this.significance = significance;
    }

    public BigDecimal getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(BigDecimal priceDifference) {
        this.priceDifference = priceDifference;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }
}
