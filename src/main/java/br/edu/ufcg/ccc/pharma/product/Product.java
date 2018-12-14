package br.edu.ufcg.ccc.pharma.product;

import br.edu.ufcg.ccc.pharma.model.AbstractEntity;
import br.edu.ufcg.ccc.pharma.category.Category;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product extends AbstractEntity {
    @NotEmpty
    private String name;
    private String producer;
    private String barcode;
    private double price;
    private int amount;
    private boolean available = true;
    @OneToOne
    private Category category;

    public Product(@NotEmpty String name, String producer, String barcode, double price, int amount, boolean available, Category category) {
        this.name = name;
        this.producer = producer;
        this.barcode = barcode;
        this.price = price;
        this.amount = amount;
        this.available = available;
        this.category = category;
    }

    public Product() {}

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
