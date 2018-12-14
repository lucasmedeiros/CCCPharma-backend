package br.edu.ufcg.ccc.pharma.order;

import br.edu.ufcg.ccc.pharma.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @Column(name = "price", nullable = false)
    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @JsonManagedReference
    @OneToMany
    @Valid
    private List<OrderProduct> orderProducts;

    public Order() {
        this.orderProducts = new ArrayList<>();
    }

    public void addOrderProduct(OrderProduct item) {
        this.orderProducts.add(item);
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
