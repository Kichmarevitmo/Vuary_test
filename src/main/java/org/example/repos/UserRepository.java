package org.example.repos;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByActivationCode(String code);
    Boolean existsByEmail(String email);
}
/*public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);

    User findByEmail(String email);
    Set<User> findByRoles(Role role);
    Boolean existsByUsername(String username);
}*/