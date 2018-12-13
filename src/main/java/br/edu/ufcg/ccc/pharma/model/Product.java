package br.edu.ufcg.ccc.pharma.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product extends AbstractEntity{
    @NotEmpty
    private String name;
    private String producer;
    private String barcode;
    @NotEmpty
    private double price;
    private boolean available = true;
    @OneToOne
    private Category category;

    public Product() {}

    public Product(@NotEmpty String name, String producer, String barcode, @NotEmpty double price, boolean available, Category category) {
        this.name = name;
        this.producer = producer;
        this.barcode = barcode;
        this.price = price;
        this.available = available;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
