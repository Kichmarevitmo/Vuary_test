package org.example;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class Application {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepo userRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public BCryptPasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @PostConstruct
    public void init() {
        if (userRepository.findByEmail("example@example.ru") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("example@example.ru");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRoles(Collections.singleton(Role.ADMIN));
            admin.setActive(true);
            userRepository.save(admin);
        }
    }
}