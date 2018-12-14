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

    @NotEmpty(message = "'price' field may not be empty")
    @Column(name = "price", nullable = false)
    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @JsonManagedReference
    @NotEmpty(message = "you need to have at least one product to order")
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Order() {

    }

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;

        List<OrderProduct> orderProducts = getOrderProducts();

        for (OrderProduct op : orderProducts) {
            sum += op.getTotalPrice();
        }

        return sum;
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
