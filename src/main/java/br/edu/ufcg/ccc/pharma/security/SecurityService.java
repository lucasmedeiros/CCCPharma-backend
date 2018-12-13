package br.edu.ufcg.ccc.pharma.security;

public interface SecurityService {

    String findLoggedInEmail();

    void autoLogin(String email, String password);
}
