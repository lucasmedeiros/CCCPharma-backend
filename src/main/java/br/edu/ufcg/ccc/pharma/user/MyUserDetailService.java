package br.edu.ufcg.ccc.pharma.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(this.userRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("CLIENT", "ADMIN");
        List<GrantedAuthority> authorityListClient = AuthorityUtils.createAuthorityList("CLIENT");

        System.out.println("TESTANDO SE EH ADMIN: " + user.isAdmin());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isAdmin() ? authorityListAdmin : authorityListClient);
    }
}
