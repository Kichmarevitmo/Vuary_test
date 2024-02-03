package org.example.controller;

import org.example.domain.Gender;
import org.example.domain.User;
import org.example.domain.WorkerRole;
import org.example.domain.equipment.image.Image;
import org.example.domain.equipment.image.ImageRepository;
import org.example.domain.equipment.image.ImageService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("genders", Gender.values());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, @RequestParam Gender gender, String lastname, WorkerRole workerRole,
                          String city, Map<String, Object> model, String email,
                          @RequestParam MultipartFile imageUSER, @RequestParam Date dateOfBirth) {
        try {
            if (imageUSER != null && !imageUSER.isEmpty()) {
                System.out.println("Добавяем изображение" + imageUSER.getName());
                Image image = imageService.uploadImage(imageUSER);
                image.setUser(user);
                user.addImage(image);
                System.out.println("Изображение в списке:" + user.getFirstImage().getName());
                System.out.println("Идентификатор родителя:" + user.getFirstImage().getUser().getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки (например, возврат сообщения об ошибке)
            return "error";
        }
        user.setGender(gender);
        user.setEmail(email);
        user.setWorkerRoles(Collections.singleton(workerRole));
        user.setDateOfBirth(dateOfBirth);
        if (!userService.addUser(user, gender, lastname, workerRole, city)) {
            model.put("message", "Аккаунт с такой почтой уже существует!");
            return "registration";
        } else {
            user.setLastName(lastname);
            user.setCity(city);
        }
        model.put("user", user);
        model.put("activationCode",user.getActivationCode());
        return "redirect:/registration";
    }

    @GetMapping("/activate/{code}")
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
}