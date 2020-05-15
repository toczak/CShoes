package pl.potoczak.cshoes.model;

import pl.potoczak.cshoes.model.parameters.*;
import pl.potoczak.cshoes.model.parameters_match.ColorMatch;
import pl.potoczak.cshoes.model.parameters_match.GenderGroupMatch;
import pl.potoczak.cshoes.model.parameters_match.ManufacturerMatch;
import pl.potoczak.cshoes.model.parameters_match.TypeMatch;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToOne(mappedBy = "shoes", cascade = CascadeType.ALL)
    private SizesShoes sizesShoes;

    @OneToMany(mappedBy = "shoes", cascade = CascadeType.ALL)
    private List<ColorMatch> colorMatchList;

    @OneToMany(mappedBy = "shoes", cascade = CascadeType.ALL)
    private List<GenderGroupMatch> genderGroupMatchList;

    @OneToMany(mappedBy = "shoes", cascade = CascadeType.ALL)
    private List<ManufacturerMatch> manufacturerMatchList;

    @OneToMany(mappedBy = "shoes", cascade = CascadeType.ALL)
    private List<TypeMatch> typeMatchList;

    @OneToMany(mappedBy = "shoes")
    private List<ShopShoesOffer> offerList;

    @OneToOne
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

    @Override
    public int hashCode() {
        return Math.toIntExact(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Shoes) {
            Shoes shoes2 = (Shoes) obj;
            return this.name.equals(shoes2.name) && (this.id.equals(shoes2.id));
        }
        return false;
    }

    public List<ColorMatch> getColorMatchList() {
        return colorMatchList;
    }

    public void setColorMatchList(List<ColorMatch> colorMatchList) {
        this.colorMatchList = colorMatchList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public SizesShoes getSizesShoes() {
        return sizesShoes;
    }

    public void setSizesShoes(SizesShoes sizesShoes) {
        this.sizesShoes = sizesShoes;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public List<GenderGroupMatch> getGenderGroupMatchList() {
        return genderGroupMatchList;
    }

    public void setGenderGroupMatchList(List<GenderGroupMatch> genderGroupMatchList) {
        this.genderGroupMatchList = genderGroupMatchList;
    }

    public List<ManufacturerMatch> getManufacturerMatchList() {
        return manufacturerMatchList;
    }

    public void setManufacturerMatchList(List<ManufacturerMatch> manufacturerMatchList) {
        this.manufacturerMatchList = manufacturerMatchList;
    }

    public List<TypeMatch> getTypeMatchList() {
        return typeMatchList;
    }

    public void setTypeMatchList(List<TypeMatch> typeMatchList) {
        this.typeMatchList = typeMatchList;
    }

    public Shoes() {
    }

    public Shoes(String name, BigDecimal price, Picture picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }
}
