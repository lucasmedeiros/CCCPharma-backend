package br.edu.ufcg.ccc.pharma.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameIgnoreCaseContaining(String fistName);

    User findByEmail(String email);
}
