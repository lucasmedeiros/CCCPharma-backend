package br.edu.ufcg.ccc.pharma.security;

import br.edu.ufcg.ccc.pharma.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    public String generate(User user) {

        Claims claims = Jwts.claims()
                .setSubject(user.getEmail())
                .setId(Long.toString(user.getId()));
        claims.put("password", user.getPassword());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }
}
