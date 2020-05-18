package pl.potoczak.cshoes.model.parameters_match;

import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.Type;

import javax.persistence.*;

@Entity
public class TypeMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TypeMatch() {
    }

    public TypeMatch(Type type, Shoes shoes, int value) {
        this.type = type;
        this.shoes = shoes;
        this.value = value;
    }
}
