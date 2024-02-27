package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="acceptable_value_boiler")
@Setter
@Getter
public class AcceptableValue_Boiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AcceptableValue acceptableValue;

    @ManyToOne
    private Boiler boiler;

}
