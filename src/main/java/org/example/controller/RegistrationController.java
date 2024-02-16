package org.example.controller;

/*@Controller
public class RegistrationController {
    private JWTGenerator jwtGenerator;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private final UserService userService;
    private UserRepository userRepository;
    private final ImageService imageService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public RegistrationController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }
    @PostMapping("/my-login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Role roles = new Role();
        roles.setName("USER");
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    /*GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        String login = request.get("login");
        String password = request.get("password");
        // Проверка логина и пароля
        if (!isValidEmail(login)) {
            response.put("status", "error");
            response.put("notify", "invalid data");
            response.put("answer", "incorrect login");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!isValidPassword(login, password)) {
            response.put("status", "error");
            response.put("notify", "invalid data");
            response.put("answer", "incorrect password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // В случае успешного входа
        response.put("status", "success");
        response.put("answer", "login successful");
        return ResponseEntity.ok(response);

    }*/
    /*@GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        Map<String,String> errors = new HashMap<>();

        // В случае успешного входа
        response.put("status", "success");
        response.put("answer", "login successful");
        response.put("notify", "valid data");
        String login = request.get("login");
        String password = request.get("password");
        errors.put("login","");
        errors.put("password","");
        response.put("errors", errors);

        // Проверка логина и пароля
        if (!isValidEmail(login)) {
            response.put("status", "error");
            errors.put("login","incorrect login");
        }
        if (isValidPassword(login, password)) {
            response.put("status", "error");
            errors.put("password","incorrect password");
        }

        if(response.get("status").equals("error"))
        {
            response.put("notify", "invalid data");
            response.put("answer", "login error");
            response.put("errors", errors);
        }
        return ResponseEntity.ok(response);
    }*/
/*
    private boolean isValidPassword(String login, String password) {
        // Получить информацию о пользователе по логину из UserService
        User user = (User) userService.loadUserByUsername(login);
        // Проверить, совпадает ли введенный пароль с паролем пользователя
        return user != null && userService.isValidPassword(user, password);
    }

    private boolean isValidEmail(String email) {
        // Проверяем существует ли пользователь с этим email
        User user = (User) userService.loadUserByUsername(email);
        return user != null;
    }

    private boolean isValidEmailRegistration(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Проверка на пустоту
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        // Проверка формата российского номера телефона
        // Паттерн для российского номера: +7(XXX)-XXX-XX-XX или 8(XXX)-XXX-XX-XX
        // где X - цифра от 0 до 9
        String regex = "^\\+?[78]\\d{10}$";
        return phoneNumber.matches(regex);
    }

    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody Map<String, String> request) throws ParseException {

        Map<String, String> errors = new HashMap<>();
        String typeOfWork = request.get("type_of_worker");
        String firstName = request.get("first_name");
        String lastName = request.get("last_name");
        String dateOfBirth = request.get("date_of_birth");
        String phone = request.get("phone");
        String email = request.get("email");
        String password = request.get("password");
        // Проверка данных
        if (!isValidTypeOfWork(typeOfWork)) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("type_of_worker", "incorrect type of worker");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        // Проверка поля 'first_name'
        if (firstName.isEmpty()) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("first_name", "first name cannot be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Проверка поля 'last_name'
        if (lastName.isEmpty()) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("last_name", "last name cannot be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Проверка поля 'date_of_birth'
        if (dateOfBirth == null) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("date_of_birth", "invalid date of birth");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Проверка поля 'phone'
        if (!isValidPhoneNumber(phone)) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("phone", "incorrect phone number format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Проверка поля 'email'
        if (!isValidEmailRegistration(email)) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("email", "incorrect email format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Проверка поля 'password'
        if (password.length() < 4) {
            errors.put("status", "error");
            errors.put("notify", "invalid data");
            errors.put("password", "password should be at least 4 characters long");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // Создание и сохранение пользователя
        User user = new User();
        user.setEmail(email);
        /*user.setRoles(Collections.singleton(user.getRoles().get()));*/
       /* user.setUsername(firstName);
        user.setLastName(lastName);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        user.setDateOfBirth(format.parse(dateOfBirth));
        user.setWorkerRoles(Collections.singleton(WorkerRole.valueOf(typeOfWork)));
        Set<WorkerRole> workerRoles = user.getWorkerRoles();
        WorkerRole firstWorkerRole = workerRoles.isEmpty() ? null : workerRoles.iterator().next();
        user.setPassword(password);
        //userService.addUser(user, lastName, firstWorkerRole);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("notify", "registration successful");
        response.put("activation_code", user.getActivationCode());
        // Возврат успешного ответа в формате JSON
        return ResponseEntity.ok(response);
    }

    private boolean isValidTypeOfWork(String typeOfWork) {
        // Проверка корректности значения поля type_of_worler
        return typeOfWork != null && (typeOfWork.equals("RETAIL_CUSTOMER")
                || typeOfWork.equals("SELLER")
                || typeOfWork.equals("INSTALLER")
                || typeOfWork.equals("GRO_SPECIALIST")
                || typeOfWork.equals("ACS_SPECIALIST")
        );
    }

    @GetMapping("/activate")
    public ResponseEntity<Map<String, Object>> activate(@RequestParam("code") String code) {
        try {
            boolean isActivated = userService.activateUser(code);
            if (isActivated) {
                // Аккаунт успешно активирован
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("notify", "Account successfully activated");
                response.put("answer", "activate success");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                // Введенный код не совпадает с истинным или не найден
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("notify", "Invalid code");
                response.put("answer", "activate error");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (UserServiceException ex) {
            // Обработка исключения UserServiceException
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("notify", "invalid code");
            response.put("answer", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @RequestMapping(value="/custom-logout", method = RequestMethod.GET)
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {
        // Get the Spring Authentication object of the current request.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // In case you are not filtering the users of this request url.
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication); // <= This is the call you are looking for.
        }
        return "redirect:/login-page";
    }
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        Map<String, Object> answer = new HashMap<>();
        answer.put("status", "success");
        answer.put("role", "USER");
        answer.put("type_of_worker", "seller");
        answer.put("first_name", user.getUsername());
        answer.put("last_name", user.getLastName());

        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        Map<String, Object> answer = new HashMap<>();
        answer.put("status", "success");
        answer.put("notify", "get profile");
        answer.put("type_of_worker", user.getWorkerRoles());
        answer.put("photo", null);
        answer.put("last name", user.getLastName());
        answer.put("date_of_birth", user.getDateOfBirth());
        answer.put("phone", user.getPhoneNumber());
        answer.put("email", user.getEmail());
        answer.put("username", "nickname");

        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

}
*/
/* @GetMapping("/activate/{code}")
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
    }*/
/*@GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("genders", Gender.values());
        return "registration";
    }*/

    /*@PostMapping("/registration")
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
    }*/