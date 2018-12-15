package br.edu.ufcg.ccc.pharma.security;

import br.edu.ufcg.ccc.pharma.user.User;
import br.edu.ufcg.ccc.pharma.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public User signup(User user) {

        user.setRole("CLIENT");

        return this.userService.save(user);
    }

    public User authenticate(User user) {
        return this.userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
