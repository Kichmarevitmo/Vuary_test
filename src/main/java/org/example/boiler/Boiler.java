package org.example.boiler;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Boiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Characteristic> characteristics;

    @ManyToOne
    private Type type;

    @ManyToOne
    private Series series;

    public Boiler() {
        this.characteristics = new ArrayList<>();
    }

}
