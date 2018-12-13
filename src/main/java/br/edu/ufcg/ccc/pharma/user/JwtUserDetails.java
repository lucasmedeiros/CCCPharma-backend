package br.edu.ufcg.ccc.pharma.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private String email;
    private Long id;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(String email, Long id, String token, List<GrantedAuthority> authorities) {
        this.email = email;
        this.id = id;
        this.token = token;
        this.authorities = authorities;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
