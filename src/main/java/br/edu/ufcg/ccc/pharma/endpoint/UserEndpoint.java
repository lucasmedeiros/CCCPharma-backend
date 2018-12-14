package br.edu.ufcg.ccc.pharma.endpoint;

import br.edu.ufcg.ccc.pharma.model.User;
import br.edu.ufcg.ccc.pharma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(this.userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/sign-up")
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/sign-up")
    public ResponseEntity<?> update(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.NO_CONTENT);
    }
}