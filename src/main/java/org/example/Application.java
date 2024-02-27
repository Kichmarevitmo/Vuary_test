package org.example;

import org.example.entities.*;
import org.example.models.enums.ERole;
import org.example.repositories.UserRepository;
import org.example.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Application {

    // признак пустой бд
    private boolean SchemaIsEmpty = false;

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
            if (SchemaIsEmpty) {
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

    @Bean
    public CommandLineRunner seederDataBase(
            TypeService typeService,
            KindService kindService,
            UnitService unitService,
            SeriesService seriesService,
            CharacteristicService characteristicService,
            CharacteristicSeriesService characteristicSeriesService,
            AttributeService attributeService
            ) {
        return args -> {
            if (SchemaIsEmpty) {

                // Типы котлов
                //------------------------------------------------------------------------------------------------------
                Type t1 = new Type("TOIVO", "Котлы настенные газовые", "null");
                Type t2 = new Type("SUARI", "Водонагреватели проточные газовые", "null");
                Type t3 = new Type("AINOVA", "Котлы настенные электрические", "null");
                Type t4 = new Type("SALMI", "Водонагреватели электрические накопительные", "null");

                // добавили в бд
                typeService.addAll(List.of(t1, t2, t3, t4));

                // Виды котлов
                //------------------------------------------------------------------------------------------------------
                // TODO продолжить набирать виды котлов
                Kind k1 = new Kind("Одноконтурные", "(с закрытой камерой) без трёхходового клапана", t1);

                // TODO добавили в бд
                kindService.addAll(List.of(k1));

                // Единиицы измерения
                //------------------------------------------------------------------------------------------------------
                // TODO продолжить набирать единицы измерения
                Unit u1 = new Unit("кВт", "Киловатт");

                // TODO добавили в бд
                unitService.addAll(List.of(u1));

                // Серии котлов
                //------------------------------------------------------------------------------------------------------
                // TODO продолжить набирать
                Series s1 = new Series(
                        "Настенный одноконтурный котел (с закрытой камерой сгорания) без трехходового клапана",
                        k1);

                //TODO добавили в бд
                seriesService.addAll(List.of(s1));

                // Характеристики
                //------------------------------------------------------------------------------------------------------
                // TODO продолжить набирать
                Characteristic c1 = new Characteristic(
                        "Макс./мин. тепловая мощность в режиме отопление",
                        u1);

                //TODO добавили в бд
                characteristicService.addAll(List.of(c1));

                // Характеристики к сериям
                //------------------------------------------------------------------------------------------------------
                // TODO продолжить набирать
                CharacteristicSeries cs1 = new CharacteristicSeries(c1, s1);

                //TODO добавили в бд
                characteristicSeriesService.addAll(List.of(cs1));

                // Аттрибуты
                //------------------------------------------------------------------------------------------------------
                // TODO продолжить набирать
                Attribute a1 = new Attribute("Высококачественная латунная гидрогруппа", s1);

                //TODO добавили в бд
                attributeService.addAll(List.of(a1));
            }
        };
    }
}

