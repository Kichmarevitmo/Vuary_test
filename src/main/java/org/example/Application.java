package org.example;

import org.example.models.enums.ERole;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("kotitonttu@example.com").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("hFaeQssuyNkoNEe9rtcM");
                admin.setEmail("kotitonttu@example.com");
                admin.setActive(true);
                admin.setRole(ERole.ROLE_ADMIN);
                // Добавьте другие необходимые поля пользователя
                userRepository.save(admin);
            }
        };
    }
}
//TODO выяснить количество котлов, если котлов меньше или больше заданного количества
//TODO убиваем все котлы и заполняем заново
