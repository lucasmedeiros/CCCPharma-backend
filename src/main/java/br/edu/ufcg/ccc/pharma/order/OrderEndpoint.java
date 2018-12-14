package br.edu.ufcg.ccc.pharma.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("orders")
public class OrderEndpoint {
    private final OrderService service;

    @Autowired
    public OrderEndpoint(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(this.service.create(order), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(this.service.update(order), HttpStatus.NO_CONTENT);
    }
}
