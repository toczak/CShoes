package pl.potoczak.cshoes.model;

import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.GenderGroup;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ShopNegotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ShopShoesOffer shopShoesOffer;

    @ManyToOne
    private ShopAgent shopAgent;

    private BigDecimal price;

    private boolean isSold;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GenderGroup genderGroup;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShopShoesOffer getShopShoesOffer() {
        return shopShoesOffer;
    }

    public void setShopShoesOffer(ShopShoesOffer shopShoesOffer) {
        this.shopShoesOffer = shopShoesOffer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GenderGroup getGenderGroup() {
        return genderGroup;
    }

    public void setGenderGroup(GenderGroup genderGroup) {
        this.genderGroup = genderGroup;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ShopAgent getShopAgent() {
        return shopAgent;
    }

    public void setShopAgent(ShopAgent shopAgent) {
        this.shopAgent = shopAgent;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}
