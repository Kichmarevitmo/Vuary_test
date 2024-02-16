package org.example.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.exception.ResourceNotFoundException;
import org.example.model.User;
import org.example.repos.UserRepository;
import org.example.security.JwtAuthResponse;
import org.example.service.UserService;
import org.example.token.TokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,20}$");
    public static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$", Pattern.CASE_INSENSITIVE);

    private boolean isValidPassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
        return matcher.matches();
    }
    /*
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) throws ParseException {
        return new ResponseEntity<>(userService.register(userDto), HttpStatus.CREATED);
    }
    */
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserDto userDto) throws ParseException {
            Map<String, Object> response = new HashMap<>();
            Map<String, String> errors = new HashMap<>();
            response.put("status", "success");
            response.put("notify", "registration success");
            response.put("answer", "registration success");
            errors.put("username", "");
            errors.put("password", "");
            errors.put("email", "");
            errors.put("lastname", "");
            errors.put("phoneNumber", "");
            errors.put("workerRole", "");
            errors.put("dateOfBirth", "");

            if(userDto.getUsername() == null) {
                errors.put("username", "incorrect username");
            }
            if(userDto.getPassword() == null || !isValidPassword(userDto.getPassword())) {
                errors.put("password", "incorrect password");
            }
            if(userDto.getEmail() == null) {
                errors.put("email", "incorrect email");
            }
            if(userDto.getLastname() == null) {
                errors.put("lastname", "incorrect lastname");
            }
            if(userDto.getPhoneNumber() == null || !isValidPhoneNumber(userDto.getPhoneNumber())) {
                errors.put("phoneNumber", "incorrect phoneNumber");
            }
            if(userDto.getWorkerRole() == null) {
                errors.put("workerRole", "incorrect workerRole");
            }
            if(userDto.getDateOfBirth() == null) {
                errors.put("dateOfBirth", "incorrect dateOfBirth");
            }
            Integer count = 0;
            for (Map.Entry<String, String> entry : errors.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                // Проверяем, что ключ не пустой и значение пустое
                if (!key.isEmpty() && value.isEmpty()) {
                    count++;
                }
            }
            if (count == 7){
                response.put("errors", errors);
                UserDto registeredUser = userService.register(userDto);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

                response.put("status", "error");
                response.put("notify", "invalid data");
                response.put("answer", "registration error");
                response.put("errors", errors);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /*@PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }*/

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        response.put("status", "success");
        response.put("notify", "login success");
        response.put("answer", "login success");
        errors.put("email", "");
        errors.put("password", "");
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getUsernameOrEmail());
        System.out.println("User Optional: " + userOptional);
        if (userOptional.isEmpty()) {
            errors.put("email", "incorrect email");
        } else {
            User user =  userOptional.get();
            if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                errors.put("password", "incorrect password");
            }
        }
        int count = 0;
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (!key.isEmpty() && value.isEmpty()) {
                count++;
            }
        }
        if (count == 2){
            response.put("errors", errors);
            var token = userService.login(loginDto);
            response.put("accessToken", token.getAccessToken());
            response.put("tokenType", token.getTokenType());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("status", "error");
        response.put("notify", "invalid data");
        response.put("answer", "login error");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    /*@GetMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String bearerToken) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        response.put("status", "success");
        response.put("notify", "logout success");
        response.put("answer", "logout success");
        String jwtToken = bearerToken.substring(7);
        if(tokenRepository.findByToken(jwtToken).orElse(null) == null) {

        }
        userService.logout(bearerToken);

        return ResponseEntity.ok("Logged out successfully");
    }*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String bearerToken) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("notify", "logout success");
        response.put("answer", "logout success");

        userService.logout(bearerToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUser(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> answer = new HashMap<>();
        response.put("status", "success");
        answer.put("role",userService.getUser(token).getRoles().stream().findFirst().get().getName().toString());
        answer.put("type_of_worker",userService.getUser(token).getWorkerRoles().stream().findFirst().get().toString());
        answer.put("first_name",userService.getUser(token).getUsername());
        answer.put("last_name",userService.getUser(token).getLastname());
        response.put("answer",answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<Object> getProfile(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> answer = new HashMap<>();
        response.put("status", "success");
        response.put("notify", "get profile");
        answer.put("phone",userService.getUser(token).getPhoneNumber());
        answer.put("date_of_birth",userService.getUser(token).getDateOfBirth().toString());
        answer.put("type_of_worker",userService.getUser(token).getWorkerRoles().stream().findFirst().get().toString());
        answer.put("first_name",userService.getUser(token).getUsername());
        answer.put("last_name",userService.getUser(token).getLastname());
        answer.put("email",userService.getUser(token).getEmail());
        response.put("answer",answer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Admin Panel");
    }
    @PostMapping("/activate")
    public ResponseEntity<Object> activateUser(@RequestBody Map<String, String> requestBody) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        response.put("status", "success");
        response.put("notify", "activate success");
        response.put("answer", "activate success");
        errors.put("code", "");

        String code = requestBody.get("code");

        if (userRepository.findByActivationCode(code) == null) {
            errors.put("code", "incorrect code");
        }

        int count = 0;
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (!key.isEmpty() && value.isEmpty()) {
                count++;
            }
        }

        if (count == 1) {
            boolean isActivated = userService.activateUser(code);
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("notify", "invalid data");
        response.put("answer", "login error");
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    /*@GetMapping("/activate")
    public ResponseEntity<String> activateUser(Model model, @RequestParam("code") String code) {
        boolean isActivated = userService.activateUser(code);
        model.addAttribute("activationCode", code);
        if (isActivated) {
            return ResponseEntity.ok("Аккаунт успешно подтвержден");
        } else {
            return ResponseEntity.ok("Код активации не найден");
        }
    }*/
}
/*@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/image/{imageName}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        byte[] imageBytes = imageService.downloadImage(imageName);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }

    @GetMapping("/getByEmail")
    @ResponseBody
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            return ResponseEntity.ok().body(userEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/delete/{userEntity}")
    @ResponseBody
    public String deleteUser(@PathVariable UserEntity userEntity) {
        userRepository.delete(userEntity);
        return "redirect:/user";
    }

    @GetMapping("/current-user")
    @ResponseBody
    public Map<String, String> getUser(@AuthenticationPrincipal UserEntity userEntityDetails) {
        Map<String, String> response = new HashMap<>();
        response.put("email", userEntityDetails.getEmail());
        response.put("username", userEntityDetails.getUsername());
        response.put("lastname", userEntityDetails.getLastName());
        response.put("dateOfBirth", userEntityDetails.getDateOfBirth().toString());
        response.put("workerRole", userEntityDetails.getWorkerRoles().toString());
        if (userEntityDetails.getFirstImage().getName() != null) {
            response.put("imageName", userEntityDetails.getFirstImage().getName());
        }
        return response;
    }
}
 /*@Autowired
    private TOIVORepo toivoRepo;
    @Autowired
    private SUARIRepo suariRepo;
    @Autowired
    private SALMIRepo salmiRepo;*/

    /*@Autowired
    private AINOVARepo ainovaRepo;*/
/*@PostMapping("/addProductTOIVO")
    @ResponseBody
    public String addProductTOIVO(@RequestParam("модельTOIVO") String модельTOIVO, @RequestParam("типTOIVO") String типTOIVO,
                                  @RequestParam("максМинТепловаяМощностьОтопление") String максМинТепловаяМощностьОтопление,
                                  @RequestParam("максМинТепловаяМощностьГВС") String максМинТепловаяМощностьГВС,
                                  @RequestParam("кпд") String кпд,
                                  @RequestParam("максРасходГаза") String максРасходГаза,
                                  @RequestParam("давлениеВоздушнойПолости") String давлениеВоздушнойПолости,
                                  @RequestParam("объемРасширительногоБака") String объемРасширительногоБака,
                                  @RequestParam("давлениеВСистемеОтопления") String давлениеВСистемеОтопления,
                                  @RequestParam("диапазонТемпературы") String диапазонТемпературы,
                                  @RequestParam("производительностьНагревГВС25") String производительностьНагревГВС25,
                                  @RequestParam("производительностьНагревГВС30") String производительностьНагревГВС30,
                                  @RequestParam("минПусковойНапорВоды") String минПусковойНапорВоды,
                                  @RequestParam("максМинДавлениеВКонтуреГВС") String максМинДавлениеВКонтуреГВС,
                                  @RequestParam("присоединительныйРазмерГазовойМагистрали") String присоединительныйРазмерГазовойМагистрали,
                                  @RequestParam("патрубкиПодающейОбратнойЛинийОтопления") String патрубкиПодающейОбратнойЛинийОтопления,
                                  @RequestParam("патрубкиПодключенияХолоднойВоды") String патрубкиПодключенияХолоднойВоды,
                                  @RequestParam("номинальноеНапряжениеЧастота") String номинальноеНапряжениеЧастота,
                                  @RequestParam("потребляемаяЭлМощность") String потребляемаяЭлМощность,
                                  @RequestParam("присоединительныйРазмерДымохода") String присоединительныйРазмерДымохода,
                                  @RequestParam("классИУровеньЗащиты") String классИУровеньЗащиты,
                                  @RequestParam("типДымоудаления") String типДымоудаления, @ModelAttribute TOIVO toivo, @RequestParam("image") MultipartFile photo) {
        try {
            if (photo != null && !photo.isEmpty()) {
                toivo.addImage(imageService.uploadImage(photo));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки (например, возврат сообщения об ошибке)
            return "error";
        }
        toivo.setМодельTOIVO(модельTOIVO);
        toivo.setТипTOIVO(TOIVOtypes.valueOf(типTOIVO).getDescription());
        toivo.setТипДымоудаления(типДымоудаления);
        toivo.setКлассИУровеньЗащиты(классИУровеньЗащиты);
        toivo.setПрисоединительныйРазмерДымохода(присоединительныйРазмерДымохода);
        toivo.setПотребляемаяЭлМощность(потребляемаяЭлМощность);
        toivo.setНоминальноеНапряжениеЧастота(номинальноеНапряжениеЧастота);
        toivo.setПатрубкиПодключенияХолоднойВоды(патрубкиПодключенияХолоднойВоды);
        toivo.setПатрубкиПодающейОбратнойЛинийОтопления(патрубкиПодающейОбратнойЛинийОтопления);
        toivo.setПрисоединительныйРазмерГазовойМагистрали(присоединительныйРазмерГазовойМагистрали);
        toivo.setМаксМинДавлениеВКонтуреГВС(максМинДавлениеВКонтуреГВС);
        toivo.setМинПусковойНапорВоды(минПусковойНапорВоды);
        toivo.setПроизводительностьНагревГВС30(производительностьНагревГВС30);
        toivo.setПроизводительностьНагревГВС25(производительностьНагревГВС25);
        toivo.setДиапазонТемпературы(диапазонТемпературы);
        toivo.setДавлениеВСистемеОтопления(давлениеВСистемеОтопления);
        toivo.setОбъемРасширительногоБака(объемРасширительногоБака);
        toivo.setДавлениеВоздушнойПолости(давлениеВоздушнойПолости);
        toivo.setМаксРасходГаза(максРасходГаза);
        toivo.setКпд(кпд);
        toivo.setМаксМинТепловаяМощностьГВС(максМинТепловаяМощностьГВС);
        toivo.setМаксМинТепловаяМощностьОтопление(максМинТепловаяМощностьОтопление);
        toivoRepo.save(toivo);
        return "redirect:/user";
    }

    @PostMapping("/addProductSUARI")
    @ResponseBody
    public String addProductSUARI(@RequestParam("модельSUARI") String модельSUARI, @RequestParam("типSUARI") String типSUARI,
                                  @RequestParam("типКамерыСгорания") String типКамерыСгорания,
                                  @RequestParam("модуляцияПламени") String модуляцияПламени,
                                  @RequestParam("номинальнаяТепловаяМощность") String номинальнаяТепловаяМощность,
                                  @RequestParam("коэффициентПолезногоДействияНеМенее") String коэффициентПолезногоДействияНеМенее,
                                  @RequestParam("номинальноеДавлениеПриродногоГаза") String номинальноеДавлениеПриродногоГаза,
                                  @RequestParam("номинальноеДавлениеСжиженногоГаза") String номинальноеДавлениеСжиженногоГаза,
                                  @RequestParam("номинальныйРасходПриродногоГаза") String номинальныйРасходПриродногоГаза,
                                  @RequestParam("номинальныйРасходСжиженногоГаза") String номинальныйРасходСжиженногоГаза,
                                  @RequestParam("давлениеПодводимойВодыДляНормальнойРаботыАппарата") String давлениеПодводимойВодыДляНормальнойРаботыАппарата,
                                  @RequestParam("минимальныйРасходВодыНеобходимыйДляЗажиганияГорелки") String минимальныйРасходВодыНеобходимыйДляЗажиганияГорелки,
                                  @RequestParam("расходВодыПриНагревеНа25") String расходВодыПриНагревеНа25,
                                  @RequestParam("температураПродуктовСгоранияНеМенее") String температураПродуктовСгоранияНеМенее,
                                  @RequestParam("зажигание") String зажигание,
                                  @RequestParam("типИНапряжениеЭлементовПитания") String типИНапряжениеЭлементовПитания,
                                  @RequestParam("напряжениеИЧастота") String напряжениеИЧастота,
                                  @RequestParam("входХолоднойВоды") String входХолоднойВоды,
                                  @RequestParam("выходГорячейВоды") String выходГорячейВоды,
                                  @RequestParam("входГаза") String входГаза,
                                  @RequestParam("массаБрутто") String массаБрутто,
                                  @RequestParam("габаритныеРазмеры") String габаритныеРазмеры,
                                  @RequestParam("внутреннийДиаметрПатрубкаДымохода") String внутреннийДиаметрПатрубкаДымохода, @ModelAttribute SUARI suari, @RequestParam("imageSUARI") MultipartFile photoSUARI) {
        try {
            if (photoSUARI != null && !photoSUARI.isEmpty()) {
                suari.addImage(imageService.uploadImage(photoSUARI));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки (например, возврат сообщения об ошибке)
            return "error";
        }
        suari.setМодельSUARI(модельSUARI);
        suari.setТипSUARI(SUARItypes.valueOf(типSUARI).GetDescription());
        suari.setТипКамерыСгорания(типКамерыСгорания);
        suari.setМодуляцияПламени(модуляцияПламени);
        suari.setНоминальнаяТепловаяМощность(номинальнаяТепловаяМощность);
        suari.setКоэффициентПолезногоДействия(коэффициентПолезногоДействияНеМенее);
        suari.setНоминальноеДавлениеПриродногоГаза(номинальноеДавлениеПриродногоГаза);
        suari.setНоминальноеДавлениеСжиженногоГаза(номинальноеДавлениеСжиженногоГаза);
        suari.setНоминальныйРасходПриродногоГаза(номинальныйРасходПриродногоГаза);
        suari.setНоминальныйРасходСжиженногоГаза(номинальныйРасходСжиженногоГаза);
        suari.setДавлениеПодводимойВоды(давлениеПодводимойВодыДляНормальнойРаботыАппарата);
        suari.setМинимальныйРасходВодыДляЗажигания(минимальныйРасходВодыНеобходимыйДляЗажиганияГорелки);
        suari.setРасходВодыПриНагревеНаДельтаT25(расходВодыПриНагревеНа25);
        suari.setТемператураПродуктовСгорания(температураПродуктовСгоранияНеМенее);
        suari.setЗажиганиеАвтоматическое(зажигание);
        suari.setТипИНапряжениеЭлементовПитания(типИНапряжениеЭлементовПитания);
        suari.setНапряжениеИЧастота(напряжениеИЧастота);
        suari.setВходХолоднойВоды(входХолоднойВоды);
        suari.setВыходГорячейВоды(выходГорячейВоды);
        suari.setВходГаза(входГаза);
        suari.setМасса(массаБрутто);
        suari.setГабаритныеРазмеры(габаритныеРазмеры);
        suari.setВнутреннийДиаметрПатрубкаДымохода(внутреннийДиаметрПатрубкаДымохода);
        suariRepo.save(suari);
        return "redirect:/user";
    }

    @PostMapping("/addProductSALMI")
    @ResponseBody
    public String addProductSALMI(@RequestParam("модельSALMI") String модельSALMI, @RequestParam("типSALMI") String типSALMI,
                                  @RequestParam("объем") String объем,
                                  @RequestParam("подключениеКСетиВодоснабжения") String подключениеКСетиВодоснабжения,
                                  @RequestParam("мощность") String мощность,
                                  @RequestParam("напряжениеИЧастотаSALMI") String напряжениеИЧастотаSALMI,
                                  @RequestParam("силаТока") String силаТока,
                                  @RequestParam("рабочееДавлениеТеплоносителя") String рабочееДавлениеТеплоносителя,
                                  @RequestParam("максимальнаяТемпература") String максимальнаяТемпература,
                                  @RequestParam("термостат") String термостат,
                                  @RequestParam("аварийныйТермодатчик") String аварийныйТермодатчик,
                                  @RequestParam("уровеньВлагозащиты") String уровеньВлагозащиты,
                                  @RequestParam("нагревательныйЭлемент") String нагревательныйЭлемент,
                                  @RequestParam("размерАнода") String размерАнода,
                                  @RequestParam("входХолоднойВоды") String входХолоднойВоды,
                                  @RequestParam("выходГорячейВоды") String выходГорячейВоды,
                                  @RequestParam("габаритныеРазмеры") String габаритныеРазмеры, @ModelAttribute SALMI salmi, @RequestParam("imageSALMI") MultipartFile photoSALMI) {
        try {
            if (photoSALMI != null && !photoSALMI.isEmpty()) {
                salmi.addImage(imageService.uploadImage(photoSALMI));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки (например, возврат сообщения об ошибке)
            return "error";
        }
        salmi.setМодельSALMI(модельSALMI);
        salmi.setТипSALMI(SALMItypes.valueOf(типSALMI).GetDescription());
        salmi.setОбъем(объем);
        salmi.setПодключениеКСетиВодоснабжения(подключениеКСетиВодоснабжения);
        salmi.setМощность(мощность);
        salmi.setНапряжениеИЧастота(напряжениеИЧастотаSALMI);
        salmi.setСилаТока(силаТока);
        salmi.setРабочееДавлениеТеплоносителя(рабочееДавлениеТеплоносителя);
        salmi.setМаксимальнаяТемпература(максимальнаяТемпература);
        salmi.setТермостат(термостат);
        salmi.setАварийныйТермодатчик(аварийныйТермодатчик);
        salmi.setУровеньВлагозащиты(уровеньВлагозащиты);
        salmi.setНагревательныйЭлемент(нагревательныйЭлемент);
        salmi.setРазмерАнода(размерАнода);
        salmi.setВходХолоднойВоды(входХолоднойВоды);
        salmi.setВыходГорячейВоды(выходГорячейВоды);
        salmi.setГабаритныеРазмеры(габаритныеРазмеры);
        salmiRepo.save(salmi);
        return "redirect:/user";
    }

    @PostMapping("/addProductAINOVA")
    @ResponseBody
    public String addProductAINOVA(@RequestParam("модельAINOVA") String модельAINOVA, @RequestParam("типAINOVA") String типAINOVA,
                                   @RequestParam("мощностьAINOVA") String мощностьAINOVA,
                                   @RequestParam("напряжениеИЧастота") String напряжениеИЧастота,
                                   @RequestParam("количествоСтупенейМощности") String количествоСтупенейМощности,
                                   @RequestParam("номинальныйТокАвтоматическогоВыключателя") String номинальныйТокАвтоматическогоВыключателя,
                                   @RequestParam("сечениеТокопроводящейЖилы") String сечениеТокопроводящейЖилы,
                                   @RequestParam("wiFi") String wiFi,
                                   @RequestParam("кпд") String кпд,
                                   @RequestParam("способУправления") String способУправления,
                                   @RequestParam("использованиеВСистемахТёплыйПол") String использованиеВСистемахТёплыйПол,
                                   @RequestParam("функционалГВС") String функционалГВС,
                                   @RequestParam("встроенныйВоздухоотводчик") String встроенныйВоздухоотводчик,
                                   @RequestParam("встроенныйПредохранительныйКлапан") String встроенныйПредохранительныйКлапан,
                                   @RequestParam("встроенныйЦиркуляционныйНасос") String встроенныйЦиркуляционныйНасос,
                                   @RequestParam("расширительныйБак") String расширительныйБак,
                                   @RequestParam("настройкаРасписанияAINOVA") String настройкаРасписанияAINOVA,
                                   @RequestParam("погодозависимоеУправление") String погодозависимоеУправление,
                                   @RequestParam("возможностьПодключенияОборудования") String возможностьПодключенияОборудования,
                                   @RequestParam("рабочееДавлениеТеплоносителя") String рабочееДавлениеТеплоносителя,
                                   @RequestParam("диапазонРегулированияТемпературыТеплоносителя") String диапазонРегулированияТемпературыТеплоносителя,
                                   @RequestParam("диапазонРегулированияТемпературыВоды") String диапазонРегулированияТемпературыВоды,
                                   @RequestParam("классВлагозащищенности") String классВлагозащищенности,
                                   @RequestParam("контурОтопленияПодающаяЛиния") String контурОтопленияПодающаяЛиния,
                                   @RequestParam("контурОтопленияОбратнаяЛиния") String контурОтопленияОбратнаяЛиния,
                                   @RequestParam("массаАппаратаБрутто") String массаАппаратаБрутто,
                                   @RequestParam("габаритныеРазмеры") String габаритныеРазмеры,
                                   @ModelAttribute AINOVA ainova, @RequestParam("imageAINOVA") MultipartFile photoAINOVA) {
        try {
            if (photoAINOVA != null && !photoAINOVA.isEmpty()) {
                ainova.addImage(imageService.uploadImage(photoAINOVA));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки (например, возврат сообщения об ошибке)
            return "error";
        } catch (AINOVAException e) {
            throw new RuntimeException(e);
        }
        ainova.setМодельAINOVA(модельAINOVA);
        ainova.setТипAINOVA(AINOVAtypes.valueOf(типAINOVA).GetDescription());
        ainova.setМощность(мощностьAINOVA);
        ainova.setНапряжениеИЧастота(напряжениеИЧастота);
        ainova.setКоличествоСтупенейМощности(количествоСтупенейМощности);
        ainova.setНоминальныйТокАвтоматическогоВыключателя(номинальныйТокАвтоматическогоВыключателя);
        ainova.setСечениеТокопроводящейЖилы(сечениеТокопроводящейЖилы);
        ainova.setWiFi(wiFi);
        ainova.setКоэффициентПолезногоДействия(кпд);
        ainova.setСпособУправления(способУправления);
        ainova.setИспользованиеВСистемахТеплыйПол(использованиеВСистемахТёплыйПол);
        ainova.setФункционалГВС(функционалГВС);
        ainova.setВстроенныйВоздухоотводчик(встроенныйВоздухоотводчик);
        ainova.setВстроенныйПредохранительныйКлапан(встроенныйПредохранительныйКлапан);
        ainova.setВстроенныйЦиркуляционныйНасос(встроенныйЦиркуляционныйНасос);
        ainova.setРасширительныйБак(расширительныйБак);
        ainova.setНастройкаРасписания(настройкаРасписанияAINOVA);
        ainova.setПогодозависимоеУправление(погодозависимоеУправление);
        ainova.setВозможностьПодключенияОборудования(возможностьПодключенияОборудования);
        ainova.setРабочееДавлениеТеплоносителя(рабочееДавлениеТеплоносителя);
        ainova.setДиапазонРегулированияТемпературыТеплоносителя(диапазонРегулированияТемпературыТеплоносителя);
        ainova.setДиапазонРегулированияТемпературыВоды(диапазонРегулированияТемпературыВоды);
        ainova.setКлассВлагозащищенности(классВлагозащищенности);
        ainova.setКонтурОтопленияПодающаяЛиния(контурОтопленияПодающаяЛиния);
        ainova.setКонтурОтопленияОбратнаяЛиния(контурОтопленияОбратнаяЛиния);
        ainova.setМасса(массаАппаратаБрутто);
        ainova.setГабаритныеРазмеры(габаритныеРазмеры);
        ainovaRepo.save(ainova);
        return "redirect:/user";
    }
    */
 /*model.addAttribute("allAinova", ainovaRepo.findAll());
        model.addAttribute("allSalmi", salmiRepo.findAll());
        model.addAttribute("allSuari", suariRepo.findAll());
        model.addAttribute("allToivo", toivoRepo.findAll());*/


    /*@GetMapping("/allAINOVA")
    @ResponseBody
    public List<AINOVA> getAllProductsAINOVA() {
        List<AINOVA> ainovaList = ainovaRepo.findAll();
        return ainovaList;
    }

    @GetMapping("/allSalmi")
    @ResponseBody
    public List<SALMI> getAllProductsSALMI() {
        List<SALMI> salmiList = salmiRepo.findAll();
        return salmiList;
    }

    @GetMapping("/allSuari")
    @ResponseBody
    public ResponseEntity<List<SUARI>> getAllProductsSUARI() {
        List<SUARI> suariList = suariRepo.findAll();
        return new ResponseEntity<>(suariList, HttpStatus.OK);
    }

    @GetMapping("/allToivo")
    @ResponseBody
    public ResponseEntity<List<TOIVO>> getAllProductsTOIVO() {
        List<TOIVO> toivoList = toivoRepo.findAll();
        return new ResponseEntity<>(toivoList, HttpStatus.OK);
    }

    @GetMapping("/allImage")
    @ResponseBody
    public ResponseEntity<List<Image>> getAllProductsImage() {
        List<Image> imageList = imageRepository.findAll();
        return new ResponseEntity<>(imageList, HttpStatus.OK);
    }
    */
    /*@Autowired
    private ImageRepository imageRepository;*/
    /*@GetMapping
    @ResponseBody
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    @ResponseBody
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }*/
/*@PostMapping
    @ResponseBody
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);
        user.setEmail(form.get("email"));
        user.setLastName(form.get("lastName"));
        user.getWorkerRoles().clear();
        user.getWorkerRoles().add(WorkerRole.valueOf(form.get("workerRoles")));

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }*/