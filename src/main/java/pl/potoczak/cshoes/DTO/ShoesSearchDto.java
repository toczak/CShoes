package pl.potoczak.cshoes.dto;

import java.math.BigDecimal;

public class ShoesSearchDto {
    private int shopsNumber;
    private int who;
    private int who_important;
    private int color;
    private int color_important;
    private int category;
    private int category_important;
    private int manufacturer;
    private int manufacturer_important;
    private int size;
    private BigDecimal priceMin;
    private BigDecimal priceMax;

    public int getShopsNumber() {
        return shopsNumber;
    }

    public void setShopsNumber(int shopsNumber) {
        this.shopsNumber = shopsNumber;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }

    public int getWho_important() {
        return who_important;
    }

    public void setWho_important(int who_important) {
        this.who_important = who_important;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor_important() {
        return color_important;
    }

    public void setColor_important(int color_important) {
        this.color_important = color_important;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory_important() {
        return category_important;
    }

    public void setCategory_important(int category_important) {
        this.category_important = category_important;
    }

    public int getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getManufacturer_important() {
        return manufacturer_important;
    }

    public void setManufacturer_important(int manufacturer_important) {
        this.manufacturer_important = manufacturer_important;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

    @Override
    public String toString() {
        return "ShoesSearchDto{" +
                "shopsNumber=" + shopsNumber +
                ", who=" + who +
                ", who_important=" + who_important +
                ", color=" + color +
                ", color_important=" + color_important +
                ", category=" + category +
                ", category_important=" + category_important +
                ", manufacturer=" + manufacturer +
                ", manufacturer_important=" + manufacturer_important +
                ", size=" + size +
                ", priceMin=" + priceMin +
                ", priceMax=" + priceMax +
                '}';
    }
}
