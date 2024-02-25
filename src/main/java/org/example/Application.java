package org.example;

import org.example.model.Role;
import org.example.model.User;
import org.example.repos.RoleRepository;
import org.example.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


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
    public CommandLineRunner createAdminUser(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            // Создаем роль ROLE_ADMIN, если ее еще нет
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setName("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            // Создаем пользователя с ролью ROLE_ADMIN, если такой еще нет
            if (userRepository.findByEmail("admin@example.com").isEmpty()){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("hFaeQssuyNkoNEe9rtcM");
                admin.setEmail("kotitonttu@example.com");
                admin.setActive(true);
                admin.setRoles(Set.of(adminRole));
                // Добавьте другие необходимые поля пользователя
                userRepository.save(admin);
            }
        };
    }
}
//TODO выяснить количество котлов, если котлов меньше или больше заданного количества
//TODO убиваем все котлы и заполняем заново
