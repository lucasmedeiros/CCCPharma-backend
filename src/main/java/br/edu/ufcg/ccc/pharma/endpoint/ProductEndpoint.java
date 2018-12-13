package br.edu.ufcg.ccc.pharma.endpoint;

import br.edu.ufcg.ccc.pharma.model.Product;
import br.edu.ufcg.ccc.pharma.service.ProductBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductEndpoint {
    private final ProductBatchService service;

    @Autowired
    public ProductEndpoint(ProductBatchService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(service.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) { return new ResponseEntity<>(service.findProductById(id), HttpStatus.OK); }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) { return new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getStock(@PathVariable long id) {
        return new ResponseEntity<>(service.countStock(id), HttpStatus.OK); }
}
