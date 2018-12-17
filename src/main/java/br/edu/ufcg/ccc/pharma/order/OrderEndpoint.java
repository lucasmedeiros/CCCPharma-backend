package br.edu.ufcg.ccc.pharma.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderEndpoint {
    private final OrderService orderService;

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.orderService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/info")
    public ResponseEntity<?> getOrdersInfo() {
        return new ResponseEntity<>(this.orderService.getInformation(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody List<OrderProductDto> request) {
        return new ResponseEntity<>(this.orderService.create(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(this.orderService.update(order), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
