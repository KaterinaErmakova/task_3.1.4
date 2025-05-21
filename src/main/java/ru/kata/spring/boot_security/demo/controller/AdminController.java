package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
>>>>>>> 22efb7d (fixed)
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")

public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @ModelAttribute("currentUser")
<<<<<<< HEAD
    public User getCurrentUser(@AuthenticationPrincipal User principal) {
        return principal != null ? principal.getUser() : null;
=======
    public UserDTO getCurrentUser(@AuthenticationPrincipal User principal) {
        return principal != null ? new UserDTO(principal.getUser()) : null;
>>>>>>> 22efb7d (fixed)
    }

    @GetMapping
    public String showAllUsers(ModelMap model) {

<<<<<<< HEAD
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            String rolesStr = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(","));
            user.setRolesString(rolesStr);
=======
        List<UserDTO> users = userService.getAllUsers();

        for (UserDTO userDTO : users) {
            String rolesStr = userDTO.getRoles().stream()
                    .map(RoleDto::getName)
                    .collect(Collectors.joining(","));
            userDTO.setRolesString(rolesStr);
>>>>>>> 22efb7d (fixed)
        }

        model.addAttribute("users", users);

        model.addAttribute("allRoles", roleService.getAllRoles());
        return "showAllUsers";
    }

    @GetMapping("/add_user")
    public String showAddUserForm(ModelMap model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "addUser";
    }



    @PostMapping("/save_user")
<<<<<<< HEAD
    public String saveUser(@RequestBody User user) {
        user.setRoles(
                user.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName()))
                        .collect(Collectors.toSet())
        );

        userService.saveUser(user);
=======
    public String saveUser(@RequestBody UserDTO userDTO) {
        userDTO.setRoles(
                userDTO.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName())).map(RoleDto::new)
                        .collect(Collectors.toSet())
        );

        userService.saveUser(userDTO);
>>>>>>> 22efb7d (fixed)
        return "redirect:/admin";
    }

    @PostMapping("/update_user")
<<<<<<< HEAD
    public String updateUser(@RequestBody User user) {
        user.setRoles(
                user.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName()))
                        .collect(Collectors.toSet())
        );
        userService.updateUser(user.getId(), user);
=======
    public String updateUser(@RequestBody UserDTO userDTO) {
        userDTO.setRoles(
                userDTO.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName())).map(RoleDto::new)
                        .collect(Collectors.toSet())
        );
        userService.updateUser(userDTO.getId(), userDTO);
>>>>>>> 22efb7d (fixed)
        return "redirect:/admin";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
