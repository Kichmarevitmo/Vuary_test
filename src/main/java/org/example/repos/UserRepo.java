package org.example.repos;

import org.example.domain.Role;
import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);

    User findByEmail(String email);
    Set<User> findByRoles(Role role);
}