package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="types")
@Getter
@Setter
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String path;

    public Type() {
        this("null", "null", "null");
    }

    public Type(String title, String description, String path) {
        this.id = null;
        this.title = title;
        this.description = description;
        this.path = path;
    }
}