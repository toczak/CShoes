package pl.potoczak.cshoes.model.parameters_match;

import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Color;

import javax.persistence.*;

@Entity
public class ColorMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public ColorMatch() {
    }

    public ColorMatch(Color color, Shoes shoes, int value) {
        this.color = color;
        this.shoes = shoes;
        this.value = value;
    }
}
