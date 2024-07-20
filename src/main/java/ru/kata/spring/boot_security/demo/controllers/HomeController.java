package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/admin")
public class HomeController {

    @GetMapping("/hello")
    public String home() {
        return "admin";
    }
}
