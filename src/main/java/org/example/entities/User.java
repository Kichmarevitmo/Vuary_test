package org.example.entities;
import jakarta.persistence.*;

import lombok.*;
import org.example.models.enums.ERole;
import org.example.models.enums.WorkerRole;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Token> tokens;

    private boolean active;

    @NonNull
    private String lastname;

    private String activationCode;

    @NonNull
    private String phoneNumber;

    @ElementCollection(targetClass = WorkerRole.class, fetch = FetchType.EAGER)

    @CollectionTable(name = "user_worker_role", joinColumns = @JoinColumn(name = "user_id"))

    @Enumerated(EnumType.STRING)
    private Set<WorkerRole> workerRoles;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)

    @NonNull
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_data_id", referencedColumnName = "id")
    @NonNull
    private FileData fileData;
}