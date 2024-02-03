package org.example.domain.equipment.ainova;

import lombok.Data;
import org.example.domain.equipment.image.Image;
import org.example.exception.AINOVAException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class AINOVA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ainova_id")
    private List<Image> images = new ArrayList<>();
    private AINOVAtypes типAINOVA;
    private String мощность;
    private String напряжениеИЧастота;
    private String количествоСтупенейМощности;
    private String номинальныйТокАвтоматическогоВыключателя;
    private String сечениеТокопроводящейЖилы;
    private String wiFi;
    private String коэффициентПолезногоДействия;
    private String способУправления;
    private String использованиеВСистемахТеплыйПол;
    private String функционалГВС;
    private String встроенныйВоздухоотводчик;
    private String встроенныйПредохранительныйКлапан;
    private String встроенныйЦиркуляционныйНасос;
    private String расширительныйБак;
    private String настройкаРасписания;
    private String погодозависимоеУправление;
    private String возможностьПодключенияОборудования;
    private String рабочееДавлениеТеплоносителя;
    private String диапазонРегулированияТемпературыТеплоносителя;
    private String диапазонРегулированияТемпературыВоды;
    private String классВлагозащищенности;
    private String контурОтопленияПодающаяЛиния;
    private String контурОтопленияОбратнаяЛиния;
    private String масса;
    private String габаритныеРазмеры;
    public Image getFirstImage() {
        if (!images.isEmpty()) {
            return images.get(0);
        }
        return null; // or throw an exception if you prefer
    }
    public void addImage(Image image) throws AINOVAException {
        if (image == null) {
            throw new AINOVAException("Изображение не может быть пустым");
        }
        images.add(image);
    }

    public void setМощность(String мощность) {
        this.мощность = мощность;
    }

    public void setНапряжениеИЧастота(String напряжениеИЧастота) {
        this.напряжениеИЧастота = напряжениеИЧастота;
    }

    public void setКоличествоСтупенейМощности(String количествоСтупенейМощности) {
        this.количествоСтупенейМощности = количествоСтупенейМощности;
    }

    public void setНоминальныйТокАвтоматическогоВыключателя(String номинальныйТокАвтоматическогоВыключателя) {
        this.номинальныйТокАвтоматическогоВыключателя = номинальныйТокАвтоматическогоВыключателя;
    }

    public void setСечениеТокопроводящейЖилы(String сечениеТокопроводящейЖилы) {
        this.сечениеТокопроводящейЖилы = сечениеТокопроводящейЖилы;
    }

    public void setWiFi(String wiFi) {
        this.wiFi = wiFi;
    }

    public void setКоэффициентПолезногоДействия(String коэффициентПолезногоДействия) {
        this.коэффициентПолезногоДействия = коэффициентПолезногоДействия;
    }

    public void setСпособУправления(String способУправления) {
        this.способУправления = способУправления;
    }

    public void setИспользованиеВСистемахТеплыйПол(String использованиеВСистемахТеплыйПол) {
        this.использованиеВСистемахТеплыйПол = использованиеВСистемахТеплыйПол;
    }

    public void setФункционалГВС(String функционалГВС) {
        this.функционалГВС = функционалГВС;
    }

    public void setВстроенныйВоздухоотводчик(String встроенныйВоздухоотводчик) {
        this.встроенныйВоздухоотводчик = встроенныйВоздухоотводчик;
    }

    public void setВстроенныйПредохранительныйКлапан(String встроенныйПредохранительныйКлапан) {
        this.встроенныйПредохранительныйКлапан = встроенныйПредохранительныйКлапан;
    }

    public void setВстроенныйЦиркуляционныйНасос(String встроенныйЦиркуляционныйНасос) {
        this.встроенныйЦиркуляционныйНасос = встроенныйЦиркуляционныйНасос;
    }

    public void setРасширительныйБак(String расширительныйБак) {
        this.расширительныйБак = расширительныйБак;
    }

    public void setНастройкаРасписания(String настройкаРасписания) {
        this.настройкаРасписания = настройкаРасписания;
    }

    public void setПогодозависимоеУправление(String погодозависимоеУправление) {
        this.погодозависимоеУправление = погодозависимоеУправление;
    }

    public void setВозможностьПодключенияОборудования(String возможностьПодключенияОборудования) {
        this.возможностьПодключенияОборудования = возможностьПодключенияОборудования;
    }

    public void setРабочееДавлениеТеплоносителя(String рабочееДавлениеТеплоносителя) {
        this.рабочееДавлениеТеплоносителя = рабочееДавлениеТеплоносителя;
    }

    public void setДиапазонРегулированияТемпературыТеплоносителя(String диапазонРегулированияТемпературыТеплоносителя) {
        this.диапазонРегулированияТемпературыТеплоносителя = диапазонРегулированияТемпературыТеплоносителя;
    }

    public void setДиапазонРегулированияТемпературыВоды(String диапазонРегулированияТемпературыВоды) {
        this.диапазонРегулированияТемпературыВоды = диапазонРегулированияТемпературыВоды;
    }

    public void setКлассВлагозащищенности(String классВлагозащищенности) {
        this.классВлагозащищенности = классВлагозащищенности;
    }

    public void setКонтурОтопленияПодающаяЛиния(String контурОтопленияПодающаяЛиния) {
        this.контурОтопленияПодающаяЛиния = контурОтопленияПодающаяЛиния;
    }

    public void setКонтурОтопленияОбратнаяЛиния(String контурОтопленияОбратнаяЛиния) {
        this.контурОтопленияОбратнаяЛиния = контурОтопленияОбратнаяЛиния;
    }

    public void setМасса(String масса) {
        this.масса = масса;
    }

    public void setГабаритныеРазмеры(String габаритныеРазмеры) {
        this.габаритныеРазмеры = габаритныеРазмеры;
    }

    public void setТипAINOVA(AINOVAtypes типAINOVA) {
        this.типAINOVA = типAINOVA;
    }

    public Long getId() {
        return id;
    }

    public AINOVAtypes getТипAINOVA() {
        return типAINOVA;
    }

    public String getМощность() {
        return мощность;
    }

    public String getНапряжениеИЧастота() {
        return напряжениеИЧастота;
    }

    public String getКоличествоСтупенейМощности() {
        return количествоСтупенейМощности;
    }

    public String getНоминальныйТокАвтоматическогоВыключателя() {
        return номинальныйТокАвтоматическогоВыключателя;
    }

    public String getСечениеТокопроводящейЖилы() {
        return сечениеТокопроводящейЖилы;
    }

    public String getWiFi() {
        return wiFi;
    }

    public String getКоэффициентПолезногоДействия() {
        return коэффициентПолезногоДействия;
    }

    public String getСпособУправления() {
        return способУправления;
    }

    public String getИспользованиеВСистемахТеплыйПол() {
        return использованиеВСистемахТеплыйПол;
    }

    public String getФункционалГВС() {
        return функционалГВС;
    }

    public String getВстроенныйВоздухоотводчик() {
        return встроенныйВоздухоотводчик;
    }

    public String getВстроенныйПредохранительныйКлапан() {
        return встроенныйПредохранительныйКлапан;
    }

    public String getВстроенныйЦиркуляционныйНасос() {
        return встроенныйЦиркуляционныйНасос;
    }

    public String getРасширительныйБак() {
        return расширительныйБак;
    }

    public String getНастройкаРасписания() {
        return настройкаРасписания;
    }

    public String getПогодозависимоеУправление() {
        return погодозависимоеУправление;
    }

    public String getВозможностьПодключенияОборудования() {
        return возможностьПодключенияОборудования;
    }

    public String getРабочееДавлениеТеплоносителя() {
        return рабочееДавлениеТеплоносителя;
    }

    public String getДиапазонРегулированияТемпературыТеплоносителя() {
        return диапазонРегулированияТемпературыТеплоносителя;
    }

    public String getДиапазонРегулированияТемпературыВоды() {
        return диапазонРегулированияТемпературыВоды;
    }

    public String getКлассВлагозащищенности() {
        return классВлагозащищенности;
    }

    public String getКонтурОтопленияПодающаяЛиния() {
        return контурОтопленияПодающаяЛиния;
    }

    public String getКонтурОтопленияОбратнаяЛиния() {
        return контурОтопленияОбратнаяЛиния;
    }

    public String getМасса() {
        return масса;
    }

    public String getГабаритныеРазмеры() {
        return габаритныеРазмеры;
    }
}
