package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="kinds")
@Getter
@Setter
public class Kind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    private Type type;

    public Kind() {
        this("null", "null", new Type());
    }

    public Kind(String title, String description, Type type) {
        this.id = null;
        this.title = title;
        this.description = description;
        this.type = type;
    }
}
