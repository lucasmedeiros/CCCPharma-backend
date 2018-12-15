package br.edu.ufcg.ccc.pharma.security;

import br.edu.ufcg.ccc.pharma.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AuthenticationEndpoint {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationEndpoint(AuthenticationService userService) {
        this.authenticationService = userService;
    }

    @PostMapping(path = "/signup")
    @Transactional
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.authenticationService.signup(user), HttpStatus.CREATED);
    }

    @PostMapping(path = "/auth")
    @Transactional
    public ResponseEntity<?> authenticate(@Valid @RequestBody User user) {
        return new ResponseEntity<>(this.authenticationService.authenticate(user), HttpStatus.OK);
    }
}
