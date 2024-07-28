package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listUsers(Model model, User user, Principal principal) {
        User currentUser = userService.findUserByUsername(principal.getName());
        model.addAttribute("admin", currentUser);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", userService.findRoles());
        return "adminPage";
    }

    @GetMapping("/profile")
    public String userPage(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "adminProfile";
    }

    @GetMapping("/new")
    public String newUserForm(User user, Model model) {
        model.addAttribute("newUser", user);
        List<Role> roles = userService.findRoles();
        model.addAttribute("roles", roles);
        return "newUserForm";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        List<Role> roles = userService.findRoles();
        model.addAttribute("roles", roles);
        return "editUser";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(value = "id") long id, User user) {
        userService.updateUserById(id, user);
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}
