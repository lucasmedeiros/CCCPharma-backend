package br.edu.ufcg.ccc.pharma.order;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import br.edu.ufcg.ccc.pharma.product.Product;
import br.edu.ufcg.ccc.pharma.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderEndpoint {
    private final OrderService orderService;
    private final ProductService productService;


    @Autowired
    public OrderEndpoint(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody List<OrderProductDto> request) {
        this.validateProductsExistence(request);

        Order order = this.orderService.create(request);

        this.adjustQuantityProducts(request);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(this.orderService.update(order), HttpStatus.NO_CONTENT);
    }

    private void adjustQuantityProducts(List<OrderProductDto> request) {
        for (OrderProductDto requestItem : request) {
            Product product = requestItem.getProduct();
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
