package com.bsuir.course.controller;

import com.bsuir.course.model.Role;
import com.bsuir.course.model.User;
import com.bsuir.course.repository.RoleRepo;
import com.bsuir.course.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @GetMapping
    public String index(Model model) {
        return "redirect:/cars";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        return "loginPage";
    }

    @GetMapping("/registration")
    public String registration(Model model) { return "registrationPage"; }

    @PostMapping("/registration")
    public String registration(@RequestParam String username, @RequestParam String password, @RequestParam String choice, Model model) {
        Iterable<User> users = userRepo.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                model.addAttribute("invalidUsername", true);
                return ("registrationPage");
            }
        }
        Role role = roleRepo.findByName(choice);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User(username, passwordEncoder.encode(password), roles);
        userRepo.save(user);
        model.addAttribute("registrationMessage", true);
        return ("registrationPage");
    }
}
