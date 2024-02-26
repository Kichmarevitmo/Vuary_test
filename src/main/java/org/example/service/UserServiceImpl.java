package org.example.service;


import lombok.AllArgsConstructor;
import org.example.Images_Module.FileData;
import org.example.Images_Module.FileDataRepository;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.exception.ApiException;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.UserServiceException;
import org.example.model.Role;
import org.example.model.User;
import org.example.model.WorkerRole;
import org.example.model.enums.ERole;
import org.example.repos.RoleRepository;
import org.example.repos.UserRepository;
import org.example.security.JwtAuthResponse;
import org.example.security.JwtTokenProvider;
import org.example.token.Token;
import org.example.token.TokenRepository;
import org.example.token.TokenType;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private FileDataRepository fileDataRepository;
    //
    private final MailSender mailSender;
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
    //
    @Override
    public UserDto register(UserDto userDto, FileData fileData) throws ParseException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User already exists. Try different Email");
        }
        Set<Role> roles = new HashSet<>();
        if (roleRepository.findByName(ERole.ROLE_USER.toString()).isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_USER.toString());
            roleRepository.save(role);
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString()).orElseThrow(() -> new ResourceNotFoundException("Role not exists"));
        User user = modelMapper.map(userDto, User.class);
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        //
        user.setFileData(fileData);
        user.setLastname(userDto.getLastname());
        user.setPhoneNumber(user.getPhoneNumber());
        Set<WorkerRole> workerRoles = new HashSet<>();
        workerRoles.add(WorkerRole.valueOf(userDto.getWorkerRole()));
        user.setWorkerRoles(workerRoles);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        user.setDateOfBirth(format.parse(userDto.getDateOfBirth()));
        String activationCode = generateActivationCode();
        user.setActivationCode(activationCode);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать в Kotitonttu. Ваш код активации: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации Kotitonttu", message);
        }
        //
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
    @Override
    public UserDto register(UserDto userDto) throws ParseException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User already exists. Try different Email");
        }
        Set<Role> roles = new HashSet<>();
        if (roleRepository.findByName(ERole.ROLE_USER.toString()).isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_USER.toString());
            roleRepository.save(role);
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString()).orElseThrow(() -> new ResourceNotFoundException("Role not exists"));
        User user = modelMapper.map(userDto, User.class);
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        //
        user.setFileData(fileDataRepository.findByName("defaultAvatar.png").orElse(null));
        user.setLastname(userDto.getLastname());
        user.setPhoneNumber(user.getPhoneNumber());
        Set<WorkerRole> workerRoles = new HashSet<>();
        workerRoles.add(WorkerRole.valueOf(userDto.getWorkerRole()));
        user.setWorkerRoles(workerRoles);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        user.setDateOfBirth(format.parse(userDto.getDateOfBirth()));
        String activationCode = generateActivationCode();
        user.setActivationCode(activationCode);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать в Kotitonttu. Ваш код активации: %s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Код активации Kotitonttu", message);
        }
        //
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken(authentication);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(jwtToken);
        return jwtAuthResponse;
    }
    @Override
    public User getUser(String bearerToken) {
        String jwtToken = bearerToken.substring(7);
        var username = jwtTokenProvider.getUsername(jwtToken);
        var user = userRepository.findByEmail(username).orElse(null);
        return user;
    }
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
    @Override
    public void logout(String bearerToken) {
        String jwtToken = bearerToken.substring(7);
        var username = jwtTokenProvider.getUsername(jwtToken);
        var user = userRepository.findByEmail(username).orElse(null);
        if(tokenRepository.findByToken(jwtToken).orElse(null) == null){
            var token = savedUserToken(user, jwtToken);
            tokenRepository.save(token);
        }
    }
    @Override
    public boolean activateUser(String code) {
        User userEntity = userRepository.findByActivationCode(code);
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
    private static Token savedUserToken(User user, String jwtToken) {
        return Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .build();
    }
}
