package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="attributes")
@Getter
@Setter
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Series series;

    public Attribute() {
        this("null", new Series());
    }

    public Attribute(String title, Series series) {
        this.id = null;
        this.title = title;
        this.series = series;
    }
}
