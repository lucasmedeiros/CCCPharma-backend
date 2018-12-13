package br.edu.ufcg.ccc.pharma.security;

import br.edu.ufcg.ccc.pharma.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private static final String SECRET = "secret";

    public User validate(String token) {

        User user = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();

            user.setEmail(body.getSubject());
            user.setId(Long.parseLong(body.getId()));
            user.setRole(body.get("role").toString());
        } catch (Exception e) {
            System.out.println(e);
        }

        return user;
    }
}
