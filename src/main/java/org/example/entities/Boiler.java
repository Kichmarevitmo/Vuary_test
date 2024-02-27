package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="boilers")
@Getter
@Setter
public class Boiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Series series;

    public Boiler() {
        this("null", new Series());
    }

    public Boiler(String title, Series series) {
        this.id = null;
        this.title = title;
        this.series = series;
    }
}
