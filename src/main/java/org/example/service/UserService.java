package org.example.service;

import org.example.domain.Gender;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.domain.WorkerRole;
import org.example.exception.UserServiceException;
import org.example.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }
    private String generateActivationCode() {
        int length = 4;
        String digits = "0123456789";
        Random random = new Random();

        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(digits.charAt(random.nextInt(digits.length())));
        }

        return code.toString();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        if(user.getActivationCode() != null ) {
            throw new UserServiceException("Вы не прошли стадию подтверждения кода активации");
        }

        return user;
    }
    public boolean addUser(User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (userFromDb != null && userFromDb.getEmail().equals(user.getEmail())) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        String activationCode = generateActivationCode();
        user.setActivationCode(activationCode);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s! \n" +
                            "Добро пожаловать в Vuary. Ваш сгенерированный код: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации Vuary", message);
        }

        return true;
    }

    public boolean addUser(User user, String lastName, WorkerRole workerRole) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (userFromDb != null && userFromDb.getEmail().equals(user.getEmail())) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        String activationCode = generateActivationCode();
        user.setActivationCode(activationCode);
        user.setLastName(lastName);
        user.setWorkerRoles(Collections.singleton(workerRole));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s! \n" +
                            "Добро пожаловать в Vuary. Ваш сгенерированный код: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации Vuary", message);
        }

        return true;
    }
    public boolean addUser(User user, String lastName, WorkerRole workerRole) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (userFromDb != null && userFromDb.getEmail().equals(user.getEmail())) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        String activationCode = generateActivationCode();
        user.setActivationCode(activationCode);
        user.setLastName(lastName);
        user.setWorkerRoles(Collections.singleton(workerRole));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s! \n" +
                            "Добро пожаловать в Vuary. Ваш сгенерированный код: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации Vuary", message);
        }

        return true;
    }


    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            throw new UserServiceException("Пользователь с таким кодом активации не найден " );
        }
        if (Objects.equals(code, user.getActivationCode())) {
            user.setActivationCode(null);
            user.setActive(true);
            userRepo.save(user);
            return true;
        } else {
            throw new UserServiceException("Введенный код не совпадает с истинным" );
        }
    }
}