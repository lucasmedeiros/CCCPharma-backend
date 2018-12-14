package br.edu.ufcg.ccc.pharma.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductEndpoint {
    private final ProductService service;

    @Autowired
    public ProductEndpoint(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) { return new ResponseEntity<>(service.findById(id), HttpStatus.OK); }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) { return new ResponseEntity<>(service.save(product), HttpStatus.CREATED); }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody Product product) {
        return new ResponseEntity<>(service.update(id, product), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
