    package ru.kata.spring.boot_security.demo.service;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;
    import ru.kata.spring.boot_security.demo.dao.UserDao;
<<<<<<< HEAD
=======
    import ru.kata.spring.boot_security.demo.dto.UserDTO;
>>>>>>> 22efb7d (fixed)
    import ru.kata.spring.boot_security.demo.models.User;


    import javax.transaction.Transactional;
    import java.util.*;
<<<<<<< HEAD
=======
    import java.util.stream.Collectors;
>>>>>>> 22efb7d (fixed)


    @Service
    public class UserServiceImpl implements UserService {

        private final UserDao userDao;
<<<<<<< HEAD

        @Autowired
        public UserServiceImpl(UserDao userDao) {
            this.userDao = userDao;
=======
        private final RoleService roleService;

        @Autowired
        public UserServiceImpl(UserDao userDao, RoleService roleService) {
            this.userDao = userDao;
            this.roleService = roleService;
>>>>>>> 22efb7d (fixed)
        }

        @Override
        @Transactional
<<<<<<< HEAD
        public List<User> getAllUsers() {
            return userDao.getAllUsers();
=======
        public List<UserDTO> getAllUsers() {
            return userDao.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());
>>>>>>> 22efb7d (fixed)
        }

        @Override
        @Transactional
<<<<<<< HEAD
        public User getUserById(int id) {
            return userDao.getUserById(id);
=======
        public UserDTO getUserById(int id) {
            return new UserDTO(userDao.getUserById(id));
>>>>>>> 22efb7d (fixed)
        }

        @Override
        @Transactional
<<<<<<< HEAD
        public void saveUser(User user) {
            userDao.saveUser(user);
=======
        public void saveUser(UserDTO userDTO) {
            userDao.saveUser(userDTO.toEntity(roleService));
>>>>>>> 22efb7d (fixed)
        }

        @Override
        @Transactional
<<<<<<< HEAD
        public void updateUser(int id, User user) {
=======
        public void updateUser(int id, UserDTO userDTO) {
>>>>>>> 22efb7d (fixed)
            User updatedUser = userDao.getUserById(id);
            if (updatedUser == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
            }
<<<<<<< HEAD
            updatedUser.setName(user.getUsername());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setAge(user.getAge());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRoles(user.getRoles());
=======
            updatedUser.setName(userDTO.getName());
            updatedUser.setSurname(userDTO.getSurname());
            updatedUser.setAge(userDTO.getAge());
            updatedUser.setPhoneNumber(userDTO.getPhoneNumber());
            updatedUser.setPassword(userDTO.getPassword());
            updatedUser.setRoles(userDTO.toRolesSet(roleService));
            updatedUser.setRolesString(userDTO.getRolesString());
>>>>>>> 22efb7d (fixed)
            userDao.updateUser(updatedUser);

        }

        @Override
        @Transactional
        public void deleteUser(int id) {
<<<<<<< HEAD
            User user = getUserById(id);
            if (user != null) {
                userDao.deleteUser(user);
=======
            UserDTO userDTO = getUserById(id);
            if (userDTO != null) {
                userDao.deleteUser(userDTO.toEntity(roleService));
>>>>>>> 22efb7d (fixed)
            }
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return userDao.findByUsername(username);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userDao.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
    }
