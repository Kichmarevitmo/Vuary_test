package org.example.domain.equipment.toivo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.example.domain.equipment.image.Image;
import org.example.exception.SUARIException;
import org.example.exception.TOIVOException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class TOIVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    private TOIVOtypes типTOIVO;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "toivo_id") // Это имя колонки в таблице Image, которая будет хранить внешний ключ на TOIVO
    private List<Image> images;
    // Характеристики котла
    private String типДымоудаления;
    private String максМинТепловаяМощностьОтопление;
    private String максМинТепловаяМощностьГВС;
    private String кпд;
    private String максРасходГаза;
    private String давлениеВоздушнойПолости;
    private String объемРасширительногоБака;
    private String давлениеВСистемеОтопления;
    private String диапазонТемпературы;
    private String производительностьНагревГВС25;
    private String производительностьНагревГВС30;
    private String минПусковойНапорВоды;
    private String максМинДавлениеВКонтуреГВС;
    private String присоединительныйРазмерГазовойМагистрали;
    private String патрубкиПодающейОбратнойЛинийОтопления;
    private String патрубкиПодключенияХолоднойВоды;
    private String номинальноеНапряжениеЧастота;
    private String потребляемаяЭлМощность;
    private String присоединительныйРазмерДымохода;
    private String классИУровеньЗащиты;
    public TOIVO() {
        this.images = new ArrayList<>();
    }
    public void addImage(Image image) {
        if (image == null)
        {
            throw new TOIVOException("Изображение для TOIVO пустое");
        }
        images.add(image);
    }
    public Image getFirstImage() {
        if (!images.isEmpty()) {
            return images.get(0);
        }
        throw new TOIVOException("Изображение не найдено");
    }

    public void setТипДымоудаления(String типДымоудаления) {
        this.типДымоудаления = типДымоудаления;
    }

    public void setМаксМинТепловаяМощностьОтопление(String максМинТепловаяМощностьОтопление) {
        this.максМинТепловаяМощностьОтопление = максМинТепловаяМощностьОтопление;
    }

    public void setМаксМинТепловаяМощностьГВС(String максМинТепловаяМощностьГВС) {
        this.максМинТепловаяМощностьГВС = максМинТепловаяМощностьГВС;
    }

    public void setКпд(String кпд) {
        this.кпд = кпд;
    }

    public void setМаксРасходГаза(String максРасходГаза) {
        this.максРасходГаза = максРасходГаза;
    }

    public void setДавлениеВоздушнойПолости(String давлениеВоздушнойПолости) {
        this.давлениеВоздушнойПолости = давлениеВоздушнойПолости;
    }

    public void setОбъемРасширительногоБака(String объемРасширительногоБака) {
        this.объемРасширительногоБака = объемРасширительногоБака;
    }

    public void setДавлениеВСистемеОтопления(String давлениеВСистемеОтопления) {
        this.давлениеВСистемеОтопления = давлениеВСистемеОтопления;
    }

    public void setДиапазонТемпературы(String диапазонТемпературы) {
        this.диапазонТемпературы = диапазонТемпературы;
    }

    public void setПроизводительностьНагревГВС25(String производительностьНагревГВС25) {
        this.производительностьНагревГВС25 = производительностьНагревГВС25;
    }

    public void setПроизводительностьНагревГВС30(String производительностьНагревГВС30) {
        this.производительностьНагревГВС30 = производительностьНагревГВС30;
    }

    public void setМинПусковойНапорВоды(String минПусковойНапорВоды) {
        this.минПусковойНапорВоды = минПусковойНапорВоды;
    }

    public void setМаксМинДавлениеВКонтуреГВС(String максМинДавлениеВКонтуреГВС) {
        this.максМинДавлениеВКонтуреГВС = максМинДавлениеВКонтуреГВС;
    }

    public void setПрисоединительныйРазмерГазовойМагистрали(String присоединительныйРазмерГазовойМагистрали) {
        this.присоединительныйРазмерГазовойМагистрали = присоединительныйРазмерГазовойМагистрали;
    }

    public void setПатрубкиПодающейОбратнойЛинийОтопления(String патрубкиПодающейОбратнойЛинийОтопления) {
        this.патрубкиПодающейОбратнойЛинийОтопления = патрубкиПодающейОбратнойЛинийОтопления;
    }

    public void setПатрубкиПодключенияХолоднойВоды(String патрубкиПодключенияХолоднойВоды) {
        this.патрубкиПодключенияХолоднойВоды = патрубкиПодключенияХолоднойВоды;
    }

    public void setНоминальноеНапряжениеЧастота(String номинальноеНапряжениеЧастота) {
        this.номинальноеНапряжениеЧастота = номинальноеНапряжениеЧастота;
    }

    public void setПотребляемаяЭлМощность(String потребляемаяЭлМощность) {
        this.потребляемаяЭлМощность = потребляемаяЭлМощность;
    }

    public void setПрисоединительныйРазмерДымохода(String присоединительныйРазмерДымохода) {
        this.присоединительныйРазмерДымохода = присоединительныйРазмерДымохода;
    }

    public void setКлассИУровеньЗащиты(String классИУровеньЗащиты) {
        this.классИУровеньЗащиты = классИУровеньЗащиты;
    }

    public void setТипTOIVO(TOIVOtypes типTOIVO) {
        this.типTOIVO = типTOIVO;
    }

    public Long getId() {
        return id;
    }

    public TOIVOtypes getТипTOIVO() {
        return типTOIVO;
    }

    public String getТипДымоудаления() {
        return типДымоудаления;
    }

    public String getМаксМинТепловаяМощностьОтопление() {
        return максМинТепловаяМощностьОтопление;
    }

    public String getМаксМинТепловаяМощностьГВС() {
        return максМинТепловаяМощностьГВС;
    }

    public String getКпд() {
        return кпд;
    }

    public String getМаксРасходГаза() {
        return максРасходГаза;
    }

    public String getДавлениеВоздушнойПолости() {
        return давлениеВоздушнойПолости;
    }

    public String getОбъемРасширительногоБака() {
        return объемРасширительногоБака;
    }

    public String getДавлениеВСистемеОтопления() {
        return давлениеВСистемеОтопления;
    }

    public String getДиапазонТемпературы() {
        return диапазонТемпературы;
    }

    public String getПроизводительностьНагревГВС25() {
        return производительностьНагревГВС25;
    }

    public String getПроизводительностьНагревГВС30() {
        return производительностьНагревГВС30;
    }

    public String getМинПусковойНапорВоды() {
        return минПусковойНапорВоды;
    }

    public String getМаксМинДавлениеВКонтуреГВС() {
        return максМинДавлениеВКонтуреГВС;
    }

    public String getПрисоединительныйРазмерГазовойМагистрали() {
        return присоединительныйРазмерГазовойМагистрали;
    }

    public String getПатрубкиПодающейОбратнойЛинийОтопления() {
        return патрубкиПодающейОбратнойЛинийОтопления;
    }

    public String getПатрубкиПодключенияХолоднойВоды() {
        return патрубкиПодключенияХолоднойВоды;
    }

    public String getНоминальноеНапряжениеЧастота() {
        return номинальноеНапряжениеЧастота;
    }

    public String getПотребляемаяЭлМощность() {
        return потребляемаяЭлМощность;
    }

    public String getПрисоединительныйРазмерДымохода() {
        return присоединительныйРазмерДымохода;
    }

    public String getКлассИУровеньЗащиты() {
        return классИУровеньЗащиты;
    }
}
