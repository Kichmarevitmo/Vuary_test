package org.example.domain.equipment.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.User;
import org.example.domain.equipment.ainova.AINOVA;
import org.example.domain.equipment.salmi.SALMI;
import org.example.domain.equipment.suari.SUARI;
import org.example.domain.equipment.toivo.TOIVO;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;
    @ManyToOne
    @JoinColumn(name = "toivo_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private TOIVO toivo;
    @ManyToOne
    @JoinColumn(name = "suari_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private SUARI suari;
    @ManyToOne
    @JoinColumn(name = "salmi_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private SALMI salmi;
    @ManyToOne
    @JoinColumn(name = "ainova_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private AINOVA ainova;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private User user;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;
}
