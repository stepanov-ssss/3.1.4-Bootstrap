package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositories;
import ru.kata.spring.boot_security.demo.repositories.UserRepositories;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepositories userRepositories;
    private RoleRepositories roleRepositories;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void setUserAndRoleRepositories(UserRepositories userRepositories, RoleRepositories roleRepositories) {
        this.userRepositories = userRepositories;
        this.roleRepositories = roleRepositories;
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        return userRepositories.findByUsername(username);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepositories.findAll();
    }

    @Override
    @Transactional
    public User showUserById(Long id) {
        return userRepositories.findById(id).orElse(new User());
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositories.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepositories.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User user) {
        if (user.getPassword().isEmpty()) {
            user.setPassword(userRepositories.findByUsername(user.getUsername()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepositories.save(user);
    }

    @Override
    @Transactional
    public List<Role> findRoles() {
        return roleRepositories.findAll();
    }
}
