package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    public User getCurrentUser(@AuthenticationPrincipal User principal) {
        return principal != null ? principal.getUser() : null;
    }

    @GetMapping
    public String showAllUsers(ModelMap model) {

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            String rolesStr = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.joining(","));
            user.setRolesString(rolesStr);
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
    public String saveUser(@RequestBody User user) {
        user.setRoles(
                user.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName()))
                        .collect(Collectors.toSet())
        );

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestBody User user) {
        user.setRoles(
                user.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName()))
                        .collect(Collectors.toSet())
        );
        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
