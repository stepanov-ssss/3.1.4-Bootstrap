package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public UserController(UserService userService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userService = userService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

//    @GetMapping("/list")
//    public String showForUser(Model model, Principal principal) {
//        User user = userService.findUserByUsername(principal.getName());
//        model.addAttribute("user", user);
//        return "user";
//    }

    @GetMapping("/list")
    public String showListUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "list";
    }
}
