package br.edu.ufcg.ccc.pharma.category;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> save(@PathVariable long id, @RequestBody Category category) {
        if (category == null || !dao.existsById(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        category.setId(id);
        return new ResponseEntity(dao.save(category), HttpStatus.OK);
    }
}
