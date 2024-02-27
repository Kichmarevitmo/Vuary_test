package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    private Kind kind;

    public Series() {
        this("null", new Kind());
    }

    public Series(String description, Kind kind) {
        this.id = null;
        this.description = description;
        this.kind = kind;
    }
}
