package pl.potoczak.cshoes.model.parameters_match;

import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Manufacturer;
import pl.potoczak.cshoes.model.parameters.Type;

import javax.persistence.*;

@Entity
public class ManufacturerMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "shoes_id", nullable = false)
    private Shoes shoes;

    private int value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ManufacturerMatch() {
    }

    public ManufacturerMatch(Manufacturer manufacturer, Shoes shoes, int value) {
        this.manufacturer = manufacturer;
        this.shoes = shoes;
        this.value = value;
    }
}
