package br.edu.ufcg.ccc.pharma.endpoint;

import br.edu.ufcg.ccc.pharma.model.Category;
import br.edu.ufcg.ccc.pharma.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryEndpoint {
    private final CategoryRepository dao;

    @Autowired
    public CategoryEndpoint(CategoryRepository dao) {
        this.dao = dao;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity(dao.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category) {
        if (category == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(dao.save(category), HttpStatus.CREATED);
    }
}
