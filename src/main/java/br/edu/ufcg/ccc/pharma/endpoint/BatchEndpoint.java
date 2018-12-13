package br.edu.ufcg.ccc.pharma.endpoint;

import br.edu.ufcg.ccc.pharma.model.Batch;
import br.edu.ufcg.ccc.pharma.service.ProductBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("batches")
public class BatchEndpoint {
    private final ProductBatchService service;

    @Autowired
    public BatchEndpoint(ProductBatchService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll() { return new ResponseEntity<>(service.findAllBatches(), HttpStatus.OK); }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Batch batch) {
        return new ResponseEntity<>(service.saveBatch(batch), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) { return new ResponseEntity<>(service.findBatchById(id), HttpStatus.OK); }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) { service.deleteBatch(id); return new ResponseEntity<>(HttpStatus.OK); }

}
