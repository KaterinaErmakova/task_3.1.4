package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
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
    public UserDTO getCurrentUser(@AuthenticationPrincipal User principal) {
        return principal != null ? new UserDTO(principal.getUser()) : null;

    }

    @GetMapping
    public String showAllUsers(ModelMap model) {

        List<UserDTO> users = userService.getAllUsers();

        for (UserDTO userDTO : users) {
            String rolesStr = userDTO.getRoles().stream()
                    .map(RoleDto::getName)
                    .collect(Collectors.joining(","));
            userDTO.setRolesString(rolesStr);

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
    public String saveUser(@RequestBody UserDTO userDTO) {
        userDTO.setRoles(
                userDTO.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName())).map(RoleDto::new)
                        .collect(Collectors.toSet())
        );

        userService.saveUser(userDTO);

        return "redirect:/admin";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestBody UserDTO userDTO) {
        userDTO.setRoles(
                userDTO.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName())).map(RoleDto::new)
                        .collect(Collectors.toSet())
        );
        userService.updateUser(userDTO.getId(), userDTO);

        return "redirect:/admin";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
