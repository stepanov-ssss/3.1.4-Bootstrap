package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String helloAdmin(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "welcomeAdmin";
    }

    @GetMapping("/users")
    public String listUsers(Model model, User user) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", userService.findRoles());
        return "list";
    }

    @GetMapping("/new")
    public String newUserForm(User user, Model model) {
        model.addAttribute("newUser", user);
        List<Role> roles = userService.findRoles();
        model.addAttribute("roles", roles);
        return "newUserForm";
    }

    @PostMapping("/users")
    public String save(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
