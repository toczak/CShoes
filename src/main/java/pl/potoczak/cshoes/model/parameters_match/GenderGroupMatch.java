package pl.potoczak.cshoes.model.parameters_match;

import pl.potoczak.cshoes.model.Shoes;
import pl.potoczak.cshoes.model.parameters.Color;
import pl.potoczak.cshoes.model.parameters.GenderGroup;

import javax.persistence.*;

@Entity
public class GenderGroupMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GenderGroup genderGroup;

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

    public GenderGroup getGenderGroup() {
        return genderGroup;
    }

    public void setGenderGroup(GenderGroup genderGroup) {
        this.genderGroup = genderGroup;
    }

    public GenderGroupMatch() {
    }

    public GenderGroupMatch(GenderGroup genderGroup, Shoes shoes, int value) {
        this.genderGroup = genderGroup;
        this.shoes = shoes;
        this.value = value;
    }
}
