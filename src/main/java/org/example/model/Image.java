package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


/*@Data
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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private User user;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] imageData;
}

 */
/*@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "toivo_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private TOIVO toivo;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "suari_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private SUARI suari;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "salmi_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private SALMI salmi;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ainova_id") // имя колонки в таблице Image, хранящей внешний ключ на TOIVO
    private AINOVA ainova;*/