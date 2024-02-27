package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="acceptable_value")
@Setter
@Getter
public class AcceptableValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Characteristic characteristic;

    private String sValue;
    private Double dValue;
    private Double minValue;
    private Double maxValue;

    public AcceptableValue() {
        this(new Characteristic(), "null", 0.0, 0.0, 0.0);
    }

    public AcceptableValue(Characteristic characteristic, String sValue, Double dValue, Double minValue, Double maxValue) {
        this.id = null;
        this.characteristic = characteristic;
        this.sValue = sValue;
        this.dValue = dValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
