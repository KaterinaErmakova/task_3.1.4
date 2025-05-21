package ru.kata.spring.boot_security.demo.service;


<<<<<<< HEAD
=======
import ru.kata.spring.boot_security.demo.dto.RoleDto;
>>>>>>> 22efb7d (fixed)
import ru.kata.spring.boot_security.demo.models.Role;


import java.util.List;


public interface RoleService {

    Role findByName(String name);

<<<<<<< HEAD
    List<Role> getAllRoles();
=======
    List<RoleDto> getAllRoles();
>>>>>>> 22efb7d (fixed)



}
