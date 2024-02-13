package org.example.controller;

import org.example.domain.Gender;
import org.example.domain.Role;
import org.example.domain.User;
import org.example.domain.WorkerRole;
import org.example.domain.equipment.ainova.AINOVA;
import org.example.domain.equipment.ainova.AINOVARepo;
import org.example.domain.equipment.ainova.AINOVAtypes;
import org.example.domain.equipment.image.Image;
import org.example.domain.equipment.image.ImageRepository;
import org.example.domain.equipment.image.ImageService;
import org.example.domain.equipment.salmi.SALMI;
import org.example.domain.equipment.salmi.SALMIRepo;
import org.example.domain.equipment.salmi.SALMItypes;
import org.example.domain.equipment.suari.SUARI;
import org.example.domain.equipment.suari.SUARIRepo;
import org.example.domain.equipment.suari.SUARItypes;
import org.example.domain.equipment.toivo.TOIVO;
import org.example.domain.equipment.toivo.TOIVORepo;
import org.example.domain.equipment.toivo.TOIVOtypes;
import org.example.exception.AINOVAException;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TOIVORepo toivoRepo;
    @Autowired
    private SUARIRepo suariRepo;
    @Autowired
    private SALMIRepo salmiRepo;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AINOVARepo ainovaRepo;
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/addProductTOIVO")
    @ResponseBody
    public String addProductTOIVO(@RequestParam("модельTOIVO") String модельTOIVO,@RequestParam("типTOIVO") String типTOIVO,
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
    public String addProductSUARI(@RequestParam("модельSUARI") String модельSUARI,@RequestParam("типSUARI") String типSUARI,
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
    public String addProductSALMI(@RequestParam("модельSALMI") String модельSALMI,@RequestParam("типSALMI") String типSALMI,
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
    public String addProductAINOVA(@RequestParam("модельAINOVA") String модельAINOVA,@RequestParam("типAINOVA") String типAINOVA,
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
    @GetMapping("/image/{imageName}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        byte[] imageBytes = imageService.downloadImage(imageName);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)  // Change to appropriate content type
                .body(imageBytes);
    }
    @GetMapping
    @ResponseBody
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("allAinova", ainovaRepo.findAll());
        model.addAttribute("allSalmi", salmiRepo.findAll());
        model.addAttribute("allSuari", suariRepo.findAll());
        model.addAttribute("allToivo", toivoRepo.findAll());
        return "userList";
    }
    @GetMapping("/allAINOVA")
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
    @GetMapping("{user}")
    @ResponseBody
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }
    @GetMapping("/getByEmail")
    @ResponseBody
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
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
    }

    @GetMapping("/delete/{user}")
    @ResponseBody
    public String deleteUser(@PathVariable User user) {
        userRepo.delete(user);
        return "redirect:/user";
    }
    @GetMapping("/current-user")
    @ResponseBody
    public Map<String, String> getUser(@AuthenticationPrincipal User userDetails) {
        Map<String, String> response = new HashMap<>();
        response.put("email", userDetails.getEmail());
        response.put("username", userDetails.getUsername());
        response.put("lastname", userDetails.getLastName());
        response.put("dateOfBirth", userDetails.getDateOfBirth().toString());
        response.put("workerRole", userDetails.getWorkerRoles().toString());
        if(userDetails.getFirstImage().getName() != null) {
            response.put("imageName", userDetails.getFirstImage().getName());
        }
        return response;
    }
}