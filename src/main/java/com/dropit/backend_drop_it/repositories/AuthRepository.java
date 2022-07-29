package com.dropit.backend_drop_it.repositories;

import com.dropit.backend_drop_it.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, String> {
}
