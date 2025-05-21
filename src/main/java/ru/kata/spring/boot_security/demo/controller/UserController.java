package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import ru.kata.spring.boot_security.demo.dto.UserDTO;
>>>>>>> 22efb7d (fixed)
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
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
    public String showUserPage(@AuthenticationPrincipal UserDetails currentUser, ModelMap model) {
<<<<<<< HEAD
        User user = userService.findByUsername(currentUser.getUsername()).get();
=======
        UserDTO user = new UserDTO(userService.findByUsername(currentUser.getUsername()).get());
>>>>>>> 22efb7d (fixed)
        model.addAttribute("user", user);
        return "user";
    }
}
