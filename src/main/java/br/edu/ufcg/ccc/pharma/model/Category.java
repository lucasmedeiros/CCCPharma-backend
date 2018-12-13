package br.edu.ufcg.ccc.pharma.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Category extends AbstractEntity {
    @NotEmpty
    private String name;
    private double discount;

    public Category(@NotEmpty String name, double discount) {
        this.name = name;
        this.discount = discount;
    }

    public Category() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
