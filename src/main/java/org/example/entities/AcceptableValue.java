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

}
