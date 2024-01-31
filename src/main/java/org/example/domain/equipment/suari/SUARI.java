package org.example.domain.equipment.suari;

import lombok.Data;
import org.example.domain.equipment.ainova.AINOVAtypes;
import org.example.domain.equipment.image.Image;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class SUARI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "suari_id") // Это имя колонки в таблице Image, которая будет хранить внешний ключ на TOIVO
    private List<Image> images = new ArrayList<>();
    private SUARItypes типSUARI;
    // Характеристики котла
    private String типКамерыСгорания;
    private String модуляцияПламени;
    private String номинальнаяТепловаяМощность;
    private String коэффициентПолезногоДействия;
    private String номинальноеДавлениеПриродногоГаза;
    private String номинальноеДавлениеСжиженногоГаза;
    private String номинальныйРасходПриродногоГаза;
    private String номинальныйРасходСжиженногоГаза;
    private String давлениеПодводимойВоды;
    private String минимальныйРасходВодыДляЗажигания;
    private String расходВодыПриНагревеНаДельтаT25;
    private String температураПродуктовСгорания;
    private String зажиганиеАвтоматическое;
    private String типИНапряжениеЭлементовПитания;
    private String напряжениеИЧастота;
    private String входХолоднойВоды;
    private String выходГорячейВоды;
    private String входГаза;
    private String масса;
    private String габаритныеРазмеры;
    private String внутреннийДиаметрПатрубкаДымохода;
    public void addImage(Image image) {
        images.add(image);
    }

    public void setТипКамерыСгорания(String типКамерыСгорания) {
        this.типКамерыСгорания = типКамерыСгорания;
    }

    public void setТипSUARI(SUARItypes типSUARI) {
        this.типSUARI = типSUARI;
    }

    public void setМодуляцияПламени(String модуляцияПламени) {
        this.модуляцияПламени = модуляцияПламени;
    }

    public void setНоминальнаяТепловаяМощность(String номинальнаяТепловаяМощность) {
        this.номинальнаяТепловаяМощность = номинальнаяТепловаяМощность;
    }

    public void setКоэффициентПолезногоДействия(String коэффициентПолезногоДействия) {
        this.коэффициентПолезногоДействия = коэффициентПолезногоДействия;
    }

    public void setНоминальноеДавлениеПриродногоГаза(String номинальноеДавлениеПриродногоГаза) {
        this.номинальноеДавлениеПриродногоГаза = номинальноеДавлениеПриродногоГаза;
    }

    public void setНоминальноеДавлениеСжиженногоГаза(String номинальноеДавлениеСжиженногоГаза) {
        this.номинальноеДавлениеСжиженногоГаза = номинальноеДавлениеСжиженногоГаза;
    }

    public void setНоминальныйРасходПриродногоГаза(String номинальныйРасходПриродногоГаза) {
        this.номинальныйРасходПриродногоГаза = номинальныйРасходПриродногоГаза;
    }

    public void setНоминальныйРасходСжиженногоГаза(String номинальныйРасходСжиженногоГаза) {
        this.номинальныйРасходСжиженногоГаза = номинальныйРасходСжиженногоГаза;
    }

    public void setДавлениеПодводимойВоды(String давлениеПодводимойВоды) {
        this.давлениеПодводимойВоды = давлениеПодводимойВоды;
    }

    public void setМинимальныйРасходВодыДляЗажигания(String минимальныйРасходВодыДляЗажигания) {
        this.минимальныйРасходВодыДляЗажигания = минимальныйРасходВодыДляЗажигания;
    }

    public void setРасходВодыПриНагревеНаДельтаT25(String расходВодыПриНагревеНаДельтаT25) {
        this.расходВодыПриНагревеНаДельтаT25 = расходВодыПриНагревеНаДельтаT25;
    }

    public void setТемператураПродуктовСгорания(String температураПродуктовСгорания) {
        this.температураПродуктовСгорания = температураПродуктовСгорания;
    }

    public void setЗажиганиеАвтоматическое(String зажиганиеАвтоматическое) {
        this.зажиганиеАвтоматическое = зажиганиеАвтоматическое;
    }

    public void setТипИНапряжениеЭлементовПитания(String типИНапряжениеЭлементовПитания) {
        this.типИНапряжениеЭлементовПитания = типИНапряжениеЭлементовПитания;
    }

    public void setНапряжениеИЧастота(String напряжениеИЧастота) {
        this.напряжениеИЧастота = напряжениеИЧастота;
    }

    public void setВходХолоднойВоды(String входХолоднойВоды) {
        this.входХолоднойВоды = входХолоднойВоды;
    }

    public void setВыходГорячейВоды(String выходГорячейВоды) {
        this.выходГорячейВоды = выходГорячейВоды;
    }

    public void setВходГаза(String входГаза) {
        this.входГаза = входГаза;
    }

    public void setМасса(String масса) {
        this.масса = масса;
    }

    public void setГабаритныеРазмеры(String габаритныеРазмеры) {
        this.габаритныеРазмеры = габаритныеРазмеры;
    }

    public void setВнутреннийДиаметрПатрубкаДымохода(String внутреннийДиаметрПатрубкаДымохода) {
        this.внутреннийДиаметрПатрубкаДымохода = внутреннийДиаметрПатрубкаДымохода;
    }

    public SUARItypes getТипSUARI() {
        return типSUARI;
    }

    public String getТипКамерыСгорания() {
        return типКамерыСгорания;
    }

    public String getМодуляцияПламени() {
        return модуляцияПламени;
    }

    public String getНоминальнаяТепловаяМощность() {
        return номинальнаяТепловаяМощность;
    }

    public String getКоэффициентПолезногоДействия() {
        return коэффициентПолезногоДействия;
    }

    public String getНоминальноеДавлениеПриродногоГаза() {
        return номинальноеДавлениеПриродногоГаза;
    }

    public String getНоминальноеДавлениеСжиженногоГаза() {
        return номинальноеДавлениеСжиженногоГаза;
    }

    public String getНоминальныйРасходПриродногоГаза() {
        return номинальныйРасходПриродногоГаза;
    }

    public String getНоминальныйРасходСжиженногоГаза() {
        return номинальныйРасходСжиженногоГаза;
    }

    public String getДавлениеПодводимойВоды() {
        return давлениеПодводимойВоды;
    }

    public String getМинимальныйРасходВодыДляЗажигания() {
        return минимальныйРасходВодыДляЗажигания;
    }

    public String getРасходВодыПриНагревеНаДельтаT25() {
        return расходВодыПриНагревеНаДельтаT25;
    }

    public String getТемператураПродуктовСгорания() {
        return температураПродуктовСгорания;
    }

    public String getЗажиганиеАвтоматическое() {
        return зажиганиеАвтоматическое;
    }

    public String getТипИНапряжениеЭлементовПитания() {
        return типИНапряжениеЭлементовПитания;
    }

    public String getНапряжениеИЧастота() {
        return напряжениеИЧастота;
    }

    public String getВходХолоднойВоды() {
        return входХолоднойВоды;
    }

    public String getВыходГорячейВоды() {
        return выходГорячейВоды;
    }

    public String getВходГаза() {
        return входГаза;
    }

    public String getМасса() {
        return масса;
    }

    public String getГабаритныеРазмеры() {
        return габаритныеРазмеры;
    }

    public String getВнутреннийДиаметрПатрубкаДымохода() {
        return внутреннийДиаметрПатрубкаДымохода;
    }
}
