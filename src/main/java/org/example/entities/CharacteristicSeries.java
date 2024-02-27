package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="characteristic_series")
@Setter
@Getter
public class CharacteristicSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Characteristic characteristic;

    @ManyToOne
    private Series series;

    public CharacteristicSeries() {
        this(new Characteristic(), new Series());
    }

    public CharacteristicSeries(Characteristic characteristic, Series series) {
        this.id = null;
        this.characteristic = characteristic;
        this.series = series;
    }
}
