package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="characteristic_series")
@Setter
@Getter
public class Characteristic_Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Characteristic characteristic;

    @ManyToOne
    private Series series;
}
