/* package org.example.domain.equipment.salmi;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.example.domain.equipment.image.Image;
import org.example.exception.SALMIException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class SALMI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "salmi_id") // Это имя колонки в таблице Image, которая будет хранить внешний ключ на TOIVO
    private List<Image> images = new ArrayList<>();
    private String модельSALMI;
    private String типSALMI;
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
    public Image getFirstImage() {
        if (!images.isEmpty()) {
            return images.get(0);
        }
        throw new SALMIException("Изображение не найдено");
    }
    public void addImage(Image image) {
        if (image == null)
        {
            throw new SALMIException("Изображение для SALMI пустое");
        }
        images.add(image);
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


    public String getОбъем() {
        return объем;
    }

    public String getПодключениеКСетиВодоснабжения() {
        return подключениеКСетиВодоснабжения;
    }

    public String getМощность() {
        return мощность;
    }

    public String getНапряжениеИЧастота() {
        return напряжениеИЧастота;
    }

    public String getСилаТока() {
        return силаТока;
    }

    public String getРабочееДавлениеТеплоносителя() {
        return рабочееДавлениеТеплоносителя;
    }

    public String getМаксимальнаяТемпература() {
        return максимальнаяТемпература;
    }

    public String getТермостат() {
        return термостат;
    }

    public String getАварийныйТермодатчик() {
        return аварийныйТермодатчик;
    }

    public String getУровеньВлагозащиты() {
        return уровеньВлагозащиты;
    }

    public String getНагревательныйЭлемент() {
        return нагревательныйЭлемент;
    }

    public String getРазмерАнода() {
        return размерАнода;
    }

    public String getВходХолоднойВоды() {
        return входХолоднойВоды;
    }

    public String getВыходГорячейВоды() {
        return выходГорячейВоды;
    }

    public String getГабаритныеРазмеры() {
        return габаритныеРазмеры;
    }

    public Long getId() {
        return id;
    }

    public String getТипSALMI() {
        return типSALMI;
    }

    public void setТипSALMI(String типSALMI) {
        this.типSALMI = типSALMI;
    }

    public String getМодельSALMI() {
        return модельSALMI;
    }

    public void setМодельSALMI(String модельSALMI) {
        this.модельSALMI = модельSALMI;
    }
}
  */