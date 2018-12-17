package br.edu.ufcg.ccc.pharma.order;

import br.edu.ufcg.ccc.pharma.product.Product;

public class OrderInformation {

    private Product product;
    private Integer quantity;
    private Double totalPrice;

    public OrderInformation(Product product, Integer quantity, Double totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
