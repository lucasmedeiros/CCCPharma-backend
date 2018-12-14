package br.edu.ufcg.ccc.pharma.endpoint;

import br.edu.ufcg.ccc.pharma.security.JwtGenerator;
import br.edu.ufcg.ccc.pharma.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenEndpoint {

    private JwtGenerator generator;

    public TokenEndpoint(JwtGenerator generator) {
        this.generator = generator;
    }

    @PostMapping
    public String generate(@RequestBody final User user) {

        return generator.generate(user);
    }
}
