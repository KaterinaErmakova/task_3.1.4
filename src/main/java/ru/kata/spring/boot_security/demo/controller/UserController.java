package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("currentUser")
    public UserDTO getCurrentUser(@AuthenticationPrincipal User principal) {
        return Optional.ofNullable(principal)
                .map(User::getUser)
                .map(UserDTO::new).orElse(new UserDTO());
    }

    @GetMapping
    public String showUserPage(@AuthenticationPrincipal UserDetails currentUser, ModelMap model) {

        UserDTO user = new UserDTO(userService.findByUsername(currentUser.getUsername()).get());

        model.addAttribute("user", user);
        return "user";
    }
}
