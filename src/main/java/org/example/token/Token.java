package org.example.token;
import jakarta.persistence.*;
import lombok.*;
import org.example.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
