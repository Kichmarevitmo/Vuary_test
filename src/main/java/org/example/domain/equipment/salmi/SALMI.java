package org.example.domain.equipment.salmi;

import org.example.domain.equipment.image.Image;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SALMI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "salmi_id") // Это имя колонки в таблице Image, которая будет хранить внешний ключ на TOIVO
    private List<Image> images = new ArrayList<>();
    private SALMItypes типSALMI;
    private String объем;
    private String подключениеКСетиВодоснабжения;
    private String мощность;
    private String напряжениеИЧастота;
    private String силаТока;
    private String рабочееДавлениеТеплоносителя;
    private String максимальнаяТемпература;
    private String термостат;
    private String аварийныйТермодатчик;
    private String уровеньВлагозащиты;
    private String нагревательныйЭлемент;
    private String размерАнода;
    private String входХолоднойВоды;
    private String выходГорячейВоды;
    private String габаритныеРазмеры;
    public void addImage(Image image) {
        images.add(image);
    }

    public void setТипSALMI(SALMItypes типSALMI) {
        this.типSALMI = типSALMI;
    }

    public void setОбъем(String объем) {
        this.объем = объем;
    }

    public void setПодключениеКСетиВодоснабжения(String подключениеКСетиВодоснабжения) {
        this.подключениеКСетиВодоснабжения = подключениеКСетиВодоснабжения;
    }

    public void setМощность(String мощность) {
        this.мощность = мощность;
    }

    public void setНапряжениеИЧастота(String напряжениеИЧастота) {
        this.напряжениеИЧастота = напряжениеИЧастота;
    }

    public void setСилаТока(String силаТока) {
        this.силаТока = силаТока;
    }

    public void setРабочееДавлениеТеплоносителя(String рабочееДавлениеТеплоносителя) {
        this.рабочееДавлениеТеплоносителя = рабочееДавлениеТеплоносителя;
    }

    public void setМаксимальнаяТемпература(String максимальнаяТемпература) {
        this.максимальнаяТемпература = максимальнаяТемпература;
    }

    public void setТермостат(String термостат) {
        this.термостат = термостат;
    }

    public void setАварийныйТермодатчик(String аварийныйТермодатчик) {
        this.аварийныйТермодатчик = аварийныйТермодатчик;
    }

    public void setУровеньВлагозащиты(String уровеньВлагозащиты) {
        this.уровеньВлагозащиты = уровеньВлагозащиты;
    }

    public void setНагревательныйЭлемент(String нагревательныйЭлемент) {
        this.нагревательныйЭлемент = нагревательныйЭлемент;
    }

    public void setРазмерАнода(String размерАнода) {
        this.размерАнода = размерАнода;
    }

    public void setВходХолоднойВоды(String входХолоднойВоды) {
        this.входХолоднойВоды = входХолоднойВоды;
    }

    public void setВыходГорячейВоды(String выходГорячейВоды) {
        this.выходГорячейВоды = выходГорячейВоды;
    }

    public void setГабаритныеРазмеры(String габаритныеРазмеры) {
        this.габаритныеРазмеры = габаритныеРазмеры;
    }
}
