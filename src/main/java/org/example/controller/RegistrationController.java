package org.example.controller;

import org.example.domain.Gender;
import org.example.domain.User;
import org.example.domain.WorkerRole;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("genders", Gender.values());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam Gender gender, String lastname, WorkerRole workerRole, String city, Map<String, Object> model, String email) {
        user.setGender(gender);
        user.setEmail(email);
        user.setWorkerRoles(Collections.singleton(workerRole));
        if (!userService.addUser(user, gender, lastname, workerRole, city)) {
            model.put("message", "User exists!");
            return "registration";
        } else {
            user.setLastName(lastname);
            user.setCity(city);
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "login";
    }
}