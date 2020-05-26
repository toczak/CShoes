package pl.potoczak.cshoes.model;

import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.GenderGroup;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ChosenOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shoes shoes;

    private int size;

    private BigDecimal price;

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

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
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
}
