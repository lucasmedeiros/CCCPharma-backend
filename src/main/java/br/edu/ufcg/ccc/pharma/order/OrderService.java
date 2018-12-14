package br.edu.ufcg.ccc.pharma.order;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import br.edu.ufcg.ccc.pharma.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductService orderProductService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderProductService orderProductService) {
        this.orderRepository = orderRepository;
        this.orderProductService = orderProductService;
    }

    public Iterable<Order> findAll() {
        return this.orderRepository.findAll();
    }

    public Order update(Order order) {
        return this.orderRepository.save(order);
    }

    public Order create(List<OrderProductDto> request) {
        Order order = new Order();

        this.makeOrder(request, order);

        return this.orderRepository.save(order);
    }

    public Order findById(Long id) {
        return this.getOrder(id);
    }

    private void makeOrder(List<OrderProductDto> request, Order order) {
        order.setDateCreated(LocalDate.now());
        order.setPrice(0D);

        for(OrderProductDto itemRequest : request) {
            int quantity = itemRequest.getQuantity();
            Product product  = itemRequest.getProduct();
            Double priceWithDiscount =  product.getPriceWithDiscount();
            this.addToOrder(product, quantity, priceWithDiscount, order);
        }
    }

    private void addToOrder(Product product, int quantity, Double price, Order order) {

        if (quantity <= 0)
            throw new IllegalArgumentException("Product " + product.getName() + " not available!");

        if (quantity > product.getAmount())
            throw new IllegalArgumentException("Amount of " + product.getName() + " out of stock!");

        System.out.println(order.toString());
        System.out.println(product.toString());
        System.out.println(quantity);

        OrderProduct item = new OrderProduct(product, quantity);

        Double currentOrderPrice = order.getPrice();
        order.setPrice(currentOrderPrice + (price * quantity));

        this.orderProductService.save(item);
        order.addOrderProduct(item);
    }

    private Order getOrder(Long id) {
        Optional<Order> userOptional = this.orderRepository.findById(id);

        if (!userOptional.isPresent())
            throw new ResourceNotFoundException("User not found for ID: " + id);

        return userOptional.get();
    }
}
