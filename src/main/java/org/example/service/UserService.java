package org.example.service;

import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.model.User;
import org.example.security.JwtAuthResponse;

import java.text.ParseException;

public interface UserService {
    UserDto register(UserDto userDto) throws ParseException;
    JwtAuthResponse login(LoginDto loginDto);


    void logout(String token);
    User getUser(String token);
    public boolean activateUser(String code);
}

/*@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSender mailSender;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
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
    public boolean isValidPassword(UserEntity userEntity, String password) {
        return passwordEncoder.matches(password, userEntity.getPassword());
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        if (userEntity.getActivationCode() != null) {
            throw new UserServiceException("Вы не прошли стадию подтверждения кода активации");
        }

        return userEntity;
    }

    /*public boolean addUser(User user, String lastName, WorkerRole workerRole) {
        User userFromDb = userRepository.findByEmail(user.getEmail());

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

        userRepository.save(user);

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
*/

    /*public boolean activateUser(String code) {
        UserEntity userEntity = userRepository.findByActivationCode(code);
        if (userEntity == null) {
            throw new UserServiceException("Пользователь с таким кодом активации не найден ");
        }
        if (Objects.equals(code, userEntity.getActivationCode())) {
            userEntity.setActivationCode(null);
            userEntity.setActive(true);
            userRepository.save(userEntity);
            return true;
        } else {
            throw new UserServiceException("Введенный код не совпадает с истинным");
        }
    }
}*/
/*public boolean addUser(User user) {
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
*/