package com.dropit.backend_drop_it.repositories;

import com.dropit.backend_drop_it.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByEmail(String email);

    Optional<RegisteredUser> findByUsername(String username);

}
