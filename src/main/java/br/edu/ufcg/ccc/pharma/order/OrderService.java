package br.edu.ufcg.ccc.pharma.order;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import br.edu.ufcg.ccc.pharma.product.Product;
import br.edu.ufcg.ccc.pharma.product.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderProductService orderProductService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderProductService orderProductService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderProductService = orderProductService;
        this.productService = productService;
    }

    public Iterable<Order> findAll() {
        return this.orderRepository.findAll();
    }

    public Order update(Order order) {
        return this.orderRepository.save(order);
    }

    public Order create(List<OrderProductDto> request) {
        this.validateProductsExistence(request);

        Order order = new Order();
        this.makeOrder(request, order);

        this.adjustQuantityProductsWhenCreated(request);

        return this.orderRepository.save(order);
    }

    public Order findById(Long id) {
        return this.getOrder(id);
    }

    public void delete(Long id) {
        Order orderToBeDeleted = getOrder(id);

        this.adjustQuantityProductsWhenDeleted(orderToBeDeleted.getOrderProducts());

        this.orderRepository.delete(orderToBeDeleted);
    }

    public List<OrderInformation> getInformation() {

        List<OrderInformation> out = new ArrayList<>();

        for (Product product : this.productService.findAll()) {
            int quantity = 0;
            double price = 0D;

            for (OrderProduct orderProduct : this.orderProductService.findAll()) {
                if (product.getId() == orderProduct.getProduct().getId()) {
                    quantity += orderProduct.getQuantity();
                    price += orderProduct.getTotalPrice();
                }
            }

            if (quantity > 0) {
                OrderInformation information = new OrderInformation(product, quantity, price);
                out.add(information);
            }
        }

        return out;
    }

    private void makeOrder(List<OrderProductDto> request, Order order) {
        order.setDateCreated(LocalDate.now());
        order.setPrice(0D);

        for(OrderProductDto itemRequest : request) {
            int quantity = itemRequest.getQuantity();
            Product product  = this.productService.getProduct(itemRequest.getProduct().getId());
            Double priceWithDiscount =  product.getPriceWithDiscount();
            this.addToOrder(product, quantity, priceWithDiscount, order);
        }
    }

    private void addToOrder(Product product, int quantity, Double price, Order order) {

        if (!product.isAvailable())
            throw new IllegalArgumentException("Product " + product.getName() + " not available!");

        if (quantity <= 0)
            throw new IllegalArgumentException("Illegal value for quantity: " + quantity);

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

    private void adjustQuantityProductsWhenDeleted(List<OrderProduct> orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            Product product = this.productService.getProduct(orderProduct.getProduct().getId());
            Integer quantity = orderProduct.getQuantity();

            Integer currentAmount = product.getAmount();

            product.setAmount(currentAmount + quantity);

            this.productService.update(product.getId(), product);
        }
    }

    private void adjustQuantityProductsWhenCreated(List<OrderProductDto> request) {
        for (OrderProductDto requestItem : request) {
            Product product = this.productService.getProduct(requestItem.getProduct().getId());
            Integer quantity = requestItem.getQuantity();

            Integer currentAmount = product.getAmount();

            product.setAmount(currentAmount - quantity);

            this.productService.update(product.getId(), product);
        }
    }

    private void validateProductsExistence(List<OrderProductDto> request) {
        List<OrderProductDto> list = request
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            throw new ResourceNotFoundException("Product not found");
        }
    }
}
