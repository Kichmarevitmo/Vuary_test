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
    //TODO: список фотографий
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL) // TODO: mapped by boiler на обратную связь с характеристикой
    // Ophfunremovul = true удалялись характеристики которые больше не используются
    private List<Characteristic> characteristics;

    @ManyToOne // join column для хранения внешнего ключа
    private Type type;

    @ManyToOne
    private Series series;

    public Boiler() {
        this.characteristics = new ArrayList<>();
    }

}
