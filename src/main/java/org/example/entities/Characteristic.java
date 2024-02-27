package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="characteristics")
@Getter
@Setter
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Unit unit;

    public Characteristic() {
        this("null", new Unit());
    }

    public Characteristic(String title, Unit unit) {
        this.id = null;
        this.title = title;
        this.unit = unit;
    }
}
