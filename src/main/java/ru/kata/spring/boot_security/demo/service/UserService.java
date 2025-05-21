package ru.kata.spring.boot_security.demo.service;




import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
<<<<<<< HEAD
=======
import ru.kata.spring.boot_security.demo.dto.UserDTO;
>>>>>>> 22efb7d (fixed)
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
<<<<<<< HEAD
    List<User> getAllUsers();
    User getUserById(int id);
    void saveUser(User user);
    void updateUser(int id, User user);
=======
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    void saveUser(UserDTO userDTO);
    void updateUser(int id, UserDTO userDTO);
>>>>>>> 22efb7d (fixed)
    void deleteUser(int id);
    Optional<User> findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
