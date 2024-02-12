package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.Gender;
import org.example.domain.User;
import org.example.domain.WorkerRole;
import org.example.domain.equipment.image.Image;
import org.example.domain.equipment.image.ImageRepository;
import org.example.domain.equipment.image.ImageService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @GetMapping("/registration")
    @ResponseBody
    public String registration(Model model) {
        model.addAttribute("genders", Gender.values());
        return "registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public String addUser(@ModelAttribute User user, String lastname, WorkerRole workerRole
            , Map<String, Object> model, String email, @RequestParam Date dateOfBirth) {
        user.setEmail(email);
        user.setWorkerRoles(Collections.singleton(workerRole));
        user.setDateOfBirth(dateOfBirth);
        if (!userService.addUser(user, lastname, workerRole)) {
            model.put("message", "Аккаунт с такой почтой уже существует!");
            return "registration";
        } else {
            user.setLastName(lastname);
        }
        model.put("user", user);
        model.put("activationCode",user.getActivationCode());
        return "redirect:/registration";
    }

    @PostMapping("/registrationByAndroid")
    public ResponseEntity<?> addUser(@ModelAttribute User user, String lastname, WorkerRole workerRole,
                                     String email, @RequestParam Date dateOfBirth) {
        user.setEmail(email);
        user.setWorkerRoles(Collections.singleton(workerRole));
        user.setDateOfBirth(dateOfBirth);
        if (!userService.addUser(user, lastname, workerRole)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Аккаунт с такой почтой уже существует!");
        } else {
            user.setLastName(lastname);
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping("/activate/{code}")
    @ResponseBody
    public String activate(Model model, @RequestParam("code") String code) {
        boolean isActivated = userService.activateUser(code);
        model.addAttribute("activationCode", code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Аккаунт успешно подтвержден");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден");
        }

        return "login";
    }
    @GetMapping("/activateByAndroid/{code}")
    @ResponseBody
    public String activateByAndroid(Model model, @RequestParam("code") String code) {
        boolean isActivated = userService.activateUser(code);
        model.addAttribute("activationCode", code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Аккаунт успешно подтвержден");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден");
        }

        return "login";
    }
}