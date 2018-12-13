package br.edu.ufcg.ccc.pharma.user;

public interface UserService {

    User findUserByEmail(String email);

    void save(User user, boolean admin);

}
