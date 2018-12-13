package br.edu.ufcg.ccc.pharma.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class Batch extends AbstractEntity {
    @NotEmpty
    private int amount;
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date expiration;
    @OneToOne
    private Product product;

    public Batch(int amount, Date expiration, Product product) {
        this.amount = amount;
        this.expiration = expiration;
        this.product = product;
    }

    public Batch() {}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
