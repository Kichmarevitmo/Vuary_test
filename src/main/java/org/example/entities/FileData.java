package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String filePath;

    /*
    public FileData() {
        this("null", "null", "null");
    }

    public FileData(String name, String type, String filePath) {
        this.id = null;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    } */
}
