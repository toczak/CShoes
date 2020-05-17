package pl.potoczak.cshoes.model;

import java.util.Objects;

public class ClientAgent {

    private String name;

    private Shoes shoes;

    private ShopShoesOffer shoesOffer;

    public ClientAgent(String name) {
        this.name = name;
    }

    public ClientAgent() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    public ShopShoesOffer getShoesOffer() {
        return shoesOffer;
    }

    public void setShoesOffer(ShopShoesOffer shoesOffer) {
        this.shoesOffer = shoesOffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAgent that = (ClientAgent) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(shoesOffer, that.shoesOffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shoesOffer);
    }

}
