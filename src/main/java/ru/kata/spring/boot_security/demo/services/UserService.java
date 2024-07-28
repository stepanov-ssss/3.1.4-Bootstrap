package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username);

    List<User> getAllUsers();

    User showUserById(Long id);

    void saveUser(User user);

    void deleteUserById(Long id);

    void updateUserById(Long id, User user);

    List<Role> findRoles();
}
