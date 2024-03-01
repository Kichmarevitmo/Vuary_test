package org.example;

import org.example.entities.*;
import org.example.models.enums.CategoryOfAdvantage;
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
            AttributeService attributeService,
            ImageForSeriesService imageForSeriesService,
            BoilerService boilerService,
            AdvantageService advantageService
            ) {
        return args -> {
            if (SchemaIsEmpty) {

                // Типы котлов
                //------------------------------------------------------------------------------------------------------
                Type t1 = new Type("TOIVO", "Котлы настенные газовые", "image_toivo.png");
                Type t2 = new Type("SUARI", "Водонагреватели проточные газовые", "image_suari.png");
                Type t3 = new Type("AINOVA", "Котлы настенные электрические", "image_ainova.png");
                Type t4 = new Type("SALMI", "Водонагреватели электрические накопительные", "image_salmi.png");

                // добавили в бд
                typeService.addAll(List.of(t1, t2, t3, t4));

                // Виды котлов
                //------------------------------------------------------------------------------------------------------
                Kind k1 = new Kind("Одноконтурные", "(с закрытой камерой) без трёхходового клапана", t1);
                Kind k2 = new Kind("Одноконтурные", "(с закрытой камерой) с трёхходовым клапаном", t1);
                Kind k3 = new Kind("Двухконтурные", "(с закрытой камерой)", t1);
                Kind k4 = new Kind("Классические модели", "(с механической регулировкой)", t2);
                Kind k5 = new Kind("Классические модели", "(с электронной модуляцией пламени)", t2);
                Kind k6 = new Kind("Полутурбо", "(дымоход в комплекте)", t2);
                Kind k7 = new Kind("Турбо", "(дымоход в комплекте)", t2);
                Kind k8 = new Kind("Стандарт", "", t3);
                Kind k9 = new Kind("Миникотельные", "", t3);
                Kind k10 = new Kind("Малый литраж", "", t4);
                Kind k11 = new Kind("Круглые эконом", "", t4);
                Kind k12 = new Kind("Круглые комфорт", "", t4);
                Kind k13 = new Kind("Плоские комфорт", "", t4);
                Kind k14 = new Kind("Плоские премиум", "", t4);
                Kind k15 = new Kind("Большой литраж", "", t4);

                kindService.addAll(List.of(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12, k13, k14, k15));

                // Единиицы измерения
                //------------------------------------------------------------------------------------------------------
                Unit u1 = new Unit("кВт", "Киловатт");
                Unit u2 = new Unit("%", "Процентов");
                Unit u3 = new Unit("м³/ч", "Кубических метров в час");
                Unit u4 = new Unit("бар", "");
                Unit u5 = new Unit("л", "Литров");
                Unit u6 = new Unit("℃", "Градусов Цельсия");
                Unit u7 = new Unit("дюйм", "Дюймов");
                Unit u8 = new Unit("В/Гц", "Вольт/Герц");
                Unit u9 = new Unit("Вт", "Ватт");
                Unit u10 = new Unit("мм", "Миллиметров");
                Unit u11 = new Unit("кг", "Килограмм");
                Unit u12 = new Unit("Па", "Паскалей");
                Unit u13 = new Unit("кг/ч", "Килограмм в час");
                Unit u14 = new Unit("МПа", "Мегапаскалей");
                Unit u15 = new Unit("л/мин", "Литров в минуту");
                Unit u16 = new Unit("м³", "Кубических метров");
                Unit u17 = new Unit("м³", "Кубических метров");
                Unit u18 = new Unit("IP", "Степень защиты");
                Unit u19 = new Unit("", "");
                Unit u20 = new Unit("B", "Вольт");
                Unit u21 = new Unit("м³", "Метров кубических");
                Unit u22 = new Unit("мм²", "Миллиметров квадратных");
                Unit u23 = new Unit("Гц", "Герц");


                unitService.addAll(List.of(u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21, u22));

                // Серии котлов
                //------------------------------------------------------------------------------------------------------
                Series s1 = new Series(
                        "Одноконтурные котелы, с закрытой камерой сгорания, без трехходового клапана, модели (T10O-T24O)", k1);
                Series s2 = new Series(
                        "Одноконтурные котелы, с закрытой камерой сгорания, с трёхходовым клапаном, модели (T10OK-T24OK)", k2);
                Series s3 = new Series("Двухконтурные котелы, с закрытой камерой сгорания, модели (T10DK-T24DK)", k3);
                Series s4 = new Series("Классические модели, с механической регулировкой, модели (S10-S12)", k4);
                Series s5 = new Series("Классические модели, с электронной модуляцией пламени, модели (S10EM-S12EM)", k5);
                Series s6 = new Series("Полутурбо, дымоход в комплекте, модели (S13ST-S16ST)", k6);
                Series s7 = new Series("Турбо, дымоход в комплекте, модели (S12FT)", k7);
                Series s8 = new Series("Стандарт, модели (LT4-24D)", k8);
                Series s9 = new Series("Миникотельные, модели (QM4-24)", k9);
                Series s10 = new Series("Малый литраж, модели (KMU10I KMU15I KMU30I)", k10);
                Series s11 = new Series("Круглые эконом, модели (VRM30 VRM50 VRM80 VRM100)", k11);
                Series s12 = new Series("Круглые комфорт, модели (VRM30D VRM50D VRM80D VRM100D)", k12);
                Series s13 = new Series("Плоские комфорт, модели (VFM30D VFM50D VFM80D)", k13);
                Series s14 = new Series("Плоские премиум, модели (VFE30WE VFE50WE VFE80WE)", k14);
                Series s15 = new Series("Большой литраж, модели (FRM200 FRM300)", k15);

                seriesService.addAll(List.of(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15));

                // Изображения котлов
                //------------------------------------------------------------------------------------------------------
                ImageForSeries i1 = new ImageForSeries("T18O_01.jpg", "image/*", "T18O_01.jpg", s1);
                ImageForSeries i2 = new ImageForSeries("T24OK_01.jpg", "image/*", "T24OK_01.jpg", s2);
                ImageForSeries i3 = new ImageForSeries("T24DK_01.jpg", "image/*", "T24DK_01.jpg", s3);
                ImageForSeries i4 = new ImageForSeries("S12_01.jpg", "image/*", "S12_01.jpg", s4);
                ImageForSeries i5 = new ImageForSeries("S10EM_01.jpg", "image/*", "S10EM_01.jpg", s5);
                ImageForSeries i6 = new ImageForSeries("S13ST_01.jpg", "image/*", "S13ST_01.jpg", s6);
                ImageForSeries i7 = new ImageForSeries("S12FT_01.jpg", "image/*", "S12FT_01.jpg", s7);
                ImageForSeries i8 = new ImageForSeries("kotitonttu-57.jpg", "image/*", "kotitonttu-57.jpg", s8);
                ImageForSeries i9 = new ImageForSeries("QM-4_06.jpg", "image/*", "QM-4_06.jpg", s9);
                ImageForSeries i10 = new ImageForSeries("KMU-10_1.jpg", "image/*", "KMU-10_1.jpg", s10);
                ImageForSeries i11 = new ImageForSeries("VRM-50_1.jpg", "image/*", "VRM-50_1.jpg", s11);
                ImageForSeries i12 = new ImageForSeries("VRM-50D_01.jpg", "image/*", "VRM-50D_01.jpg", s12);
                ImageForSeries i13 = new ImageForSeries("VFM-50D_01.jpg", "image/*", "VFM-50D_01.jpg", s13);
                ImageForSeries i14 = new ImageForSeries("VFE-50WE_01.jpg", "image/*", "VFE-50WE_01.jpg", s14);
                ImageForSeries i15 = new ImageForSeries("kotitonttu-26.jpg", "image/*", "kotitonttu-26.jpg", s15);

                imageForSeriesService.addAll(List.of(i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15));

                // Характеристики
                //------------------------------------------------------------------------------------------------------
                Characteristic c1 = new Characteristic(
                        "Макс./мин. тепловая мощность в режиме отопление",
                        u1);
                Characteristic c2 = new Characteristic(
                        "Макс./мин. теплопроизводительность",
                        u1);
                Characteristic c3 = new Characteristic(
                        "КПД не менее",
                        u2);
                Characteristic c4 = new Characteristic(
                        "Максимальный расход природного газа",
                        u3);
                Characteristic c5 = new Characteristic(
                        "Давление в воздушной полости расширительного бака",
                        u4);
                Characteristic c6 = new Characteristic(
                        "Объем расширительного бака",
                        u5);
                Characteristic c7 = new Characteristic(
                        "Давление в системе отопления",
                        u4);
                Characteristic c8 = new Characteristic(
                        "Диапазон регулировки температуры теплоносителя",
                        u6);
                Characteristic c9 = new Characteristic(
                        "Диапазон регулировки температуры бытовой горячей воды (бойлер косвенного нагрева)",
                        u6);
                Characteristic c10 = new Characteristic(
                        "Макс. давление в контуре бойлера косвенного нагрева)",
                        u4);
                Characteristic c11 = new Characteristic(
                        "Присоединительный размер газовой магистрали)",
                        u7);
                Characteristic c12 = new Characteristic(
                        "Патрубки подключения подающей и обратной линий системы отопления)",
                        u7);
                Characteristic c13 = new Characteristic(
                        "Патрубки подключения холодной воды и бойлера косвенного нагрева)",
                        u7);
                Characteristic c14 = new Characteristic(
                        "Номинальное напряжение/частота)",
                        u8);
                Characteristic c15 = new Characteristic(
                        "Потребляемая эл. мощность)",
                        u9);
                Characteristic c16 = new Characteristic(
                        "Присоединительный размер дымохода)",
                        u10);
                Characteristic c17 = new Characteristic(
                        "Класс и уровень защиты)",
                        u18);
                Characteristic c18 = new Characteristic(
                        "Вес, нетто)",
                        u11);
                Characteristic c19 = new Characteristic(
                        "Габаритные размеры)",
                        u10);
                Characteristic c20 = new Characteristic(
                        "Номинальная тепловая мощность)",
                        u1);
                Characteristic c21 = new Characteristic(
                        "Номинальная теплопроизводительность)",
                        u1);
                Characteristic c22 = new Characteristic(
                        "Номинальное давление газа)",
                        u19);
                Characteristic c23 = new Characteristic(
                        "Номинальный расход газа)",
                        u3);
                Characteristic c24 = new Characteristic(
                        "Давление подводимой воды для нормальной работы аппарата)",
                        u14);
                Characteristic c25 = new Characteristic(
                        "Расход воды при нагреве на ΔT = 25°C)",
                        u15);
                Characteristic c26 = new Characteristic(
                        "Тип и напряжение элементов питания)",
                        u20);
                Characteristic c27 = new Characteristic(
                        "Присоединительные размеры:)",
                        u19);
                Characteristic c28 = new Characteristic(
                        "Присоединительные размеры:)",
                        u19);
                Characteristic c30 = new Characteristic(
                        "Диаметр дымохода)",
                        u10);
                Characteristic c31 = new Characteristic(
                        "Тип камеры сгорания)",
                        u19);
                Characteristic c32 = new Characteristic(
                        "Тип дымоудаления)",
                        u19);
                Characteristic c33 = new Characteristic(
                        "Наличие модуляции пламени)",
                        u19);
                Characteristic c34 = new Characteristic(
                        "Расход газа)",
                        u21);
                Characteristic c35 = new Characteristic(
                        "Мин. расход воды, необходимый для зажигания горелки)",
                        u15);
                Characteristic c36 = new Characteristic(
                        "Напряжение и частота)",
                        u8);
                Characteristic c37 = new Characteristic(
                        "Зажигание)",
                        u19);
                Characteristic c39 = new Characteristic(
                        "Модель)",
                        u19);
                Characteristic c40 = new Characteristic(
                        "Номинальная потребляемая мощность)",
                        u1);
                Characteristic c41 = new Characteristic(
                        "Количество ступеней мощности)",
                        u19);
                Characteristic c43 = new Characteristic(
                        "Номинальный ток автоматического выключателя)",
                        u19);
                Characteristic c44 = new Characteristic(
                        "Сечение токопроводящей жилы)",
                        u22);
                Characteristic c45 = new Characteristic(
                        "Рабочее давление теплоносителя)",
                        u14);
                Characteristic c46 = new Characteristic(
                        "Тип системы отопления)",
                        u19);
                Characteristic c47 = new Characteristic(
                        "Диапазон регулирования температуры теплоносителя)",
                        u6);
                Characteristic c48 = new Characteristic(
                        "Диапазон регулирования температуры воды)",
                        u6);
                Characteristic c49 = new Characteristic(
                        "Класс влагозащищенности)",
                        u18);
                Characteristic c50 = new Characteristic(
                        "Циркуляционный насос)",
                        u19);
                Characteristic c51 = new Characteristic(
                        "Габаритные размеры, В*Ш*Г не более)",
                        u10);
                Characteristic c52 = new Characteristic(
                        "Масса нетто, не более)",
                        u11);
                Characteristic c56 = new Characteristic(
                        "Макс./мин. тепловая мощность в режиме ГВС)",
                        u1);
                Characteristic c57 = new Characteristic(
                        "Макс./мин. давления в контуре ГВС",
                        u4);
                Characteristic c58 = new Characteristic(
                        "Диапазон регулировки температуры бытовой горячей воды)",
                        u6);
                Characteristic c59 = new Characteristic(
                        "Производительность по нагреву горячей воды （ при △T=25℃）)",
                        u15);
                Characteristic c60 = new Characteristic(
                        "Производительность по нагреву горячей воды（при △T=30℃）",
                        u15);
                Characteristic c61 = new Characteristic(
                        "Минимальный пусковой напор воды",
                        u15);
                Characteristic c62 = new Characteristic(
                        "Присоединительный размер газовой магистрали",
                        u7);
                Characteristic c63 = new Characteristic(
                        "Патрубки подключения холодной и горячей воды",
                        u7);
                Characteristic c64 = new Characteristic(
                        "Объем",
                        u5);
                Characteristic c65 = new Characteristic(
                        "Вес нетто/брутто",
                        u11);
                Characteristic c66 = new Characteristic(
                        "Макс. раб. давление,",
                        u14);
                Characteristic c67 = new Characteristic(
                        "Номин. напряжение,",
                        u19);
                Characteristic c68 = new Characteristic(
                        "Номин. мощность",
                        u1);
                Characteristic c69 = new Characteristic(
                        "Класс защиты",
                        u19);
                Characteristic c70 = new Characteristic(
                        "Время нагрева",
                        u19); // TODO ждем ответа по пункту Время нагрева * (∆45°C) документа Passport_FRM_A5+3mm_new.pdf

                c1.setSeries(List.of(s1));
                c2.setSeries(List.of(s1, s2, s3));
                c3.setSeries(List.of(s1, s2, s3, s4, s5, s7));
                c4.setSeries(List.of(s1, s2, s3));
                c5.setSeries(List.of(s1, s2, s3));
                c6.setSeries(List.of(s1, s2, s3, s9));
                c7.setSeries(List.of(s1, s2, s3));
                c8.setSeries(List.of(s1, s2, s3));
                c9.setSeries(List.of(s1, s2));
                c10.setSeries(List.of(s1, s2));
                c11.setSeries(List.of(s1, s2));
                c12.setSeries(List.of(s1, s2, s3));
                c13.setSeries(List.of(s1, s2));
                c14.setSeries(List.of(s1, s2, s3));
                c15.setSeries(List.of(s1, s2, s3));
                c16.setSeries(List.of(s1, s2, s3));
                c17.setSeries(List.of(s1, s2, s3));
                c18.setSeries(List.of(s1, s2, s3));
                c19.setSeries(List.of(s1, s2, s3, s4, s5, s10, s11, s12, s13, s14, s15));
                c20.setSeries(List.of(s4, s5, s7));
                c21.setSeries(List.of(s4, s5));
                c22.setSeries(List.of(s4, s5, s7));
                c23.setSeries(List.of(s4, s5));
                c24.setSeries(List.of(s4, s5, s7));
                c25.setSeries(List.of(s4, s5, s7));
                c26.setSeries(List.of(s4, s5));
                c27.setSeries(List.of(s4, s5, s7, s9));
                c28.setSeries(List.of(s9));
                c30.setSeries(List.of(s4, s5));
                c31.setSeries(List.of(s7));
                c32.setSeries(List.of(s1, s2, s3, s7));
                c33.setSeries(List.of(s7));
                c34.setSeries(List.of(s7));
                c35.setSeries(List.of(s7));
                c36.setSeries(List.of(s7));
                c37.setSeries(List.of(s7));
                c39.setSeries(List.of(s9));
                c40.setSeries(List.of(s9));
                c41.setSeries(List.of(s9));
                c43.setSeries(List.of(s9));
                c44.setSeries(List.of(s9));
                c45.setSeries(List.of(s9));
                c46.setSeries(List.of(s9));
                c47.setSeries(List.of(s9));
                c48.setSeries(List.of(s9));
                c49.setSeries(List.of(s9));
                c50.setSeries(List.of(s9));
                c51.setSeries(List.of(s9));
                c52.setSeries(List.of(s9));
                c56.setSeries(List.of(s3));
                c57.setSeries(List.of(s3));
                c58.setSeries(List.of(s3));
                c59.setSeries(List.of(s3));
                c60.setSeries(List.of(s3));
                c61.setSeries(List.of(s3));
                c62.setSeries(List.of(s3));
                c63.setSeries(List.of(s3));
                c64.setSeries(List.of(s10, s11, s12, s13, s14, s15));
                c65.setSeries(List.of(s10, s11, s12, s13, s14, s15));
                c66.setSeries(List.of(s10, s11, s12, s13, s14, s15));
                c67.setSeries(List.of(s9, s10, s11, s12, s13, s14, s15));
                c68.setSeries(List.of(s10, s11, s12, s13, s14, s15));
                c69.setSeries(List.of(s10, s11, s12, s13, s14, s15));
                c70.setSeries(List.of(s10, s11, s12, s13, s14, s15));

                characteristicService.addAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19,
                        c20, c21, c22, c23, c24, c25, c26, c27, c28, c30, c31, c32, c33, c34, c35, c36, c37, c39, c40, c41,
                        c43, c44, c45, c46, c47, c48, c49, c50, c51, c52, c56, c57, c58, c59, c60, c61, c62, c63, c64, c65, c66, c67, c68, c69, c70));

                // Аттрибуты
                //------------------------------------------------------------------------------------------------------
                Attribute a1 = new Attribute("Высококачественная латунная гидрогруппа", s1);
                Attribute a2 = new Attribute("Газовый клапан Sit 845 Sigma", s1);
                Attribute a3 = new Attribute("Инверторный привод турбины", s1);
                Attribute a4 = new Attribute("Медные патрубки", s1);
                Attribute a5 = new Attribute("Цифровой датчик давления теплоносителя", s1);
                Attribute a6 = new Attribute("Высококачественная латунная гидрогруппа", s2);
                Attribute a7 = new Attribute("Газовый клапан Sit 845 Sigma", s2);
                Attribute a8 = new Attribute("Инверторный привод турбины", s2);
                Attribute a9 = new Attribute("Медные патрубки", s2);
                Attribute a10 = new Attribute("Цифровой датчик давления теплоносителя", s2);
                Attribute a11 = new Attribute("Датчик бойлера в комплекте", s2);
                Attribute a12 = new Attribute("Высококачественная латунная гидрогруппа", s3);
                Attribute a13 = new Attribute("Газовый клапан Sit 845 Sigma", s3);
                Attribute a14 = new Attribute("Инверторный привод турбины", s3);
                Attribute a15 = new Attribute("Медные патрубки", s3);
                Attribute a16 = new Attribute("Цифровой датчик давления теплоносителя", s3);
                Attribute a17 = new Attribute("Вторичный теплообменник имеет 12 пластин", s3);
                Attribute a18 = new Attribute("Классическая модель, механическая регулировка температуры", s4);
                Attribute a19 = new Attribute("Cистема электронной модуляции (EM) пламени горелки позволяет более точно поддерживать заданную температуру горячей воды в пределах 1°C вне зависимости от увеличения/уменьшения протока.", s5);
                Attribute a20 = new Attribute("В моделях «Полутурбо» с открытой камерой сгорания воздух для горения поступает из помещения, в котором установлены водонагреватель, а дымовые газы отводятся при помощи вентилятора и дымовой трубы наружу", s6);
                Attribute a21 = new Attribute("В моделях «Турбо» с закрытой камерой сгорания турбина доставляет уличный воздух для горения и выводит наружу продукты сгорания при помощи коаксиального дымохода", s7);
                Attribute a22 = new Attribute(" ", s8);
                Attribute a23 = new Attribute("Встроенный циркуляционный насос 15-PBG-6 с возможностью настройки выбега", s9);
                Attribute a24 = new Attribute("Расширительный бак объёмом 6 литров", s9);
                Attribute a25 = new Attribute("Нагревательный модуль в теплоизоляции.", s9);
                Attribute a26 = new Attribute("Аналоговый манометр", s9);
                Attribute a27 = new Attribute("Кран подпитки", s9);
                Attribute a28 = new Attribute("Круглые накопительные водонагреватели малого объёма с верхним подключением к сети водоснабжения ", s10);
                Attribute a29 = new Attribute("Круглые накопительные водонагреватели", s11);
                Attribute a30 = new Attribute("Круглые накопительные водонагреватели с цифровым дисплеем", s12);
                Attribute a31 = new Attribute("Плоские накопительные водонагреватели повышенной мощности с цифровым управлением", s13);
                Attribute a32 = new Attribute("Плоские накопительные водонагреватели повышенной мощности с управлением по Wi-Fi", s14);
                Attribute a33 = new Attribute("Мощные накопительные водонагреватели большого объёма", s15);

                attributeService.addAll(List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16,
                        a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33));

                // Котлы
                //------------------------------------------------------------------------------------------------------
                Boiler b1 = new Boiler("T10O", s1);
                Boiler b2 = new Boiler("T24O", s1);
                Boiler b3 = new Boiler("T10OK", s2);
                Boiler b4 = new Boiler("T24OK", s2);
                Boiler b5 = new Boiler("T10DK", s3);
                Boiler b6 = new Boiler("T24DK", s3);
                Boiler b7 = new Boiler("S10", s4);
                Boiler b8 = new Boiler("S12", s4);
                Boiler b9 = new Boiler("S10EM", s5);
                Boiler b10 = new Boiler("S12EM", s5);

                Boiler b11 = new Boiler("S13ST", s6);
                Boiler b12 = new Boiler("S16ST", s6);
                Boiler b13 = new Boiler("S12FT", s7);
                Boiler b14 = new Boiler("LT4", s8);
                Boiler b15 = new Boiler("24D", s8);
                Boiler b16 = new Boiler("QM4", s9);
                Boiler b17 = new Boiler("QM24", s9);
                Boiler b18 = new Boiler("KMU10I", s10);
                Boiler b19 = new Boiler("KMU15I", s10);

                Boiler b20 = new Boiler("VRM30", s11);
                Boiler b21 = new Boiler("VRM100", s11);
                Boiler b22 = new Boiler("VRM30D", s12);
                Boiler b23 = new Boiler("VRM100D", s12);
                Boiler b24 = new Boiler("VFM30D", s13);
                Boiler b25 = new Boiler("VFM80D", s13);
                Boiler b26 = new Boiler("VFE30WE", s14);
                Boiler b27 = new Boiler("VFE80WE", s14);
                Boiler b28 = new Boiler("FRM200", s15);
                Boiler b29 = new Boiler("FRM300", s15);

                boilerService.addAll(List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17,
                        b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29));

                // Преимущества
                //------------------------------------------------------------------------------------------------------
                Advantage ad1 = new Advantage("Режим работы с теплыми полами", "Operating_mode_with_heated_floors.png", CategoryOfAdvantage.COMFORT);
                Advantage ad2 = new Advantage("Возможность работы на сжиженном газе", "The_ability_to_work_on_liquefied_gas.png", CategoryOfAdvantage.COMFORT);
                Advantage ad3 = new Advantage("Низкий уровень шума", "Low_noise_level.png", CategoryOfAdvantage.COMFORT);
                Advantage ad4 = new Advantage("Цифровое управление", "Digital_control.png", CategoryOfAdvantage.COMFORT);
                Advantage ad5 = new Advantage("Высокая экономичность", "High_efficiency.png", CategoryOfAdvantage.COMFORT);
                Advantage ad6 = new Advantage("Автоматическое электронное зажигание", "Automatic_electronic_ignition.png", CategoryOfAdvantage.COMFORT);
                Advantage ad7 = new Advantage("Регулировка температуры нагрева", "Adjustment_heating_temperature.png", CategoryOfAdvantage.COMFORT);
                Advantage ad8 = new Advantage("Работа при низком давлении воды", "Job_at_low_water_pressure.png", CategoryOfAdvantage.COMFORT);
                Advantage ad9 = new Advantage("Точное поддержание температуры", "Accurate_maintaining_temperature.png", CategoryOfAdvantage.COMFORT);
                Advantage ad10 = new Advantage("Сенсорная панель управления", "Sensory_control_Panel.png", CategoryOfAdvantage.COMFORT);
                Advantage ad11 = new Advantage("Различные режимы работы", "Various_modes_work.png", CategoryOfAdvantage.COMFORT);
                Advantage ad12 = new Advantage("Управление по Wi-Fi", "Wi_Fi_control.png", CategoryOfAdvantage.COMFORT);
                Advantage ad13 = new Advantage("Индикатор нагрева", "Heating_indicator.png", CategoryOfAdvantage.COMFORT);
                Advantage ad14 = new Advantage("Оптимальная мощность", "Optimal_power.png", CategoryOfAdvantage.COMFORT);
                Advantage ad15 = new Advantage("Низкие теплопотери", "Low_heat_loss.png", CategoryOfAdvantage.COMFORT);
                Advantage ad16 = new Advantage("Повышенная мощность", "Increased_power_2.png", CategoryOfAdvantage.COMFORT);
                Advantage ad17 = new Advantage("Повышенная мощность", "Increased_power_3.png", CategoryOfAdvantage.COMFORT);
                Advantage ad18 = new Advantage("Гарантия на бак", "Guarantee_five_years.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad19 = new Advantage("Магниевый анод", "Magnesium_anode.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad20 = new Advantage("Рабочее давление", "Working_pressure.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad21 = new Advantage("Высокое качество сварных швов", "High_quality_welds.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad22 = new Advantage("Нагревательный элемент из нержавеющей стали", "A_heating_element_stainless_steel.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad23 = new Advantage("Качественные комплектующие", "Quality_components.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad24 = new Advantage("Разъёмы для подключения внешних устройств", "Connectors_to_connect_external_devices.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad25 = new Advantage("Сухой нагревательный элемент", "Dry_heating_element.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad26 = new Advantage("Нижнее подключение", "Lower_connection.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad27 = new Advantage("Гарантия два года", "Two_year_warranty.png", CategoryOfAdvantage.CONSTRUCTION);
                Advantage ad28 = new Advantage("Горелка из нержавеющей стали", "Burner_stainless_become.png", CategoryOfAdvantage.COMFORT);
                Advantage ad29 = new Advantage("Теплообменник из бескислородной меди", "Heat_exchanger_from_oxygen_free_copper.png", CategoryOfAdvantage.COMFORT);
                Advantage ad30 = new Advantage("Жаропрочная эмаль", "Heat_resistant_enamel.png", CategoryOfAdvantage.COMFORT);
                Advantage ad31 = new Advantage("Высокая ремонтопригодность", "High_maintainability.png", CategoryOfAdvantage.COMFORT);
                Advantage ad32 = new Advantage("Защита от перегрева", "Double_overheat_protection.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad33 = new Advantage("Защита от избыточного давления", "Pressure_safety_valve_protects_against_excess_water", CategoryOfAdvantage.PROTECTION);
                Advantage ad34 = new Advantage("Термостат – защита от перегрева, отключает подачу газа", "Thermostat_protection_against_overheating_disconne", CategoryOfAdvantage.PROTECTION);
                Advantage ad35 = new Advantage("Электрод ионизации – отключение подачи газа при отсутствии пламени на горелке", "Ionization_electrode_shutdown_gas_supply_when_there_is_no_flame_on_the_burner.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad36 = new Advantage("Датчик контроля тяги – защита при недостаточной или обратной тяге", "Traction_control_sensor_protection_in_case_of_insuffi", CategoryOfAdvantage.PROTECTION);
                Advantage ad37 = new Advantage("Датчик протока – контроль напора воды", "Flow_switch.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad38 = new Advantage("Обратный клапан – защита системы от замерзания", "Check_valve_protection_anti_freeze_systems.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad39 = new Advantage("Защита от утечки тока", "Residual_current_device_included.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad40 = new Advantage("Автоматический воздухоотводчик", "Auto_air_vent.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad41 = new Advantage("Индикация ошибок", "Error_indication.png", CategoryOfAdvantage.PROTECTION);
                Advantage ad42 = new Advantage("Двойной слой эмали на внутреннем баке – эффективная защита от коррозии", "Double_layer_of_enamel_on_the_inside_tank_effective_protection_against_corrosion.png", CategoryOfAdvantage.PROTECTION);

                advantageService.addAll(List.of(ad1, ad2, ad3, ad4, ad5, ad6, ad7, ad8, ad9, ad10, ad11, ad12, ad13, ad14, ad15,
                        ad16, ad17, ad18, ad19, ad20, ad21, ad22, ad23, ad24, ad25, ad26, ad27, ad28, ad29, ad30, ad31, ad32, ad33, ad34, ad35,
                        ad36, ad37, ad38, ad39, ad40, ad41, ad42));
           }
        };
    }
}

