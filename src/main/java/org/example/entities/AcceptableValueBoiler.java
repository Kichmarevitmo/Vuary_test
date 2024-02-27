package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="acceptable_value_boiler")
@Setter
@Getter
public class AcceptableValueBoiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AcceptableValue acceptableValue;

    @ManyToOne
    private Boiler boiler;

    public AcceptableValueBoiler() {
        this(new AcceptableValue(), new Boiler());
    }

    public AcceptableValueBoiler(AcceptableValue acceptableValue, Boiler boiler) {
        this.id = null;
        this.acceptableValue = acceptableValue;
        this.boiler = boiler;
    }
}
