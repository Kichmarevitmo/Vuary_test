package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.models.enums.CategoryOfAdvantage;

@Entity
@Table(name="advantages")
@Setter
@Getter
public class Advantage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name="icon_path")
    private String iconPath;

    @Enumerated(EnumType.STRING)
    private CategoryOfAdvantage category;

    @ManyToOne
    private Series series;
}
