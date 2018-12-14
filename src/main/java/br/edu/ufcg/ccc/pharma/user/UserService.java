package br.edu.ufcg.ccc.pharma.user;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@SuppressWarnings("WeakerAccess")
@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User save(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return this.getUser(id);
    }

    public void delete(Long id) {
        User userToBeDeleted = this.getUser(id);
        this.userRepository.delete(userToBeDeleted);
    }

    private User getUser(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new ResourceNotFoundException("User not found for ID: " + id);

        return userOptional.get();
    }
}
