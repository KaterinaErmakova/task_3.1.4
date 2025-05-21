package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
<<<<<<< HEAD
=======
import ru.kata.spring.boot_security.demo.dto.RoleDto;
>>>>>>> 22efb7d (fixed)
import ru.kata.spring.boot_security.demo.models.Role;


import java.util.List;
<<<<<<< HEAD
=======
import java.util.stream.Collectors;
>>>>>>> 22efb7d (fixed)


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
<<<<<<< HEAD
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
=======
    public List<RoleDto> getAllRoles() {
        return roleDao.getAllRoles().stream().map(RoleDto::new).collect(Collectors.toList());
>>>>>>> 22efb7d (fixed)
    }

}
