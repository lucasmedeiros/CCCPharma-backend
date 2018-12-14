package br.edu.ufcg.ccc.pharma.order;

import br.edu.ufcg.ccc.pharma.model.AbstractEntity;
import br.edu.ufcg.ccc.pharma.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
@Entity
public class OrderProduct extends AbstractEntity {

    @ManyToOne
    private Product product;

    @Column
    private Integer quantity;

    public OrderProduct() { }

    public OrderProduct(Product product, Integer quantity) {
        this.product = product;

        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProduct)) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(getProduct(), that.getProduct()) &&
                Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQuantity());
    }
}