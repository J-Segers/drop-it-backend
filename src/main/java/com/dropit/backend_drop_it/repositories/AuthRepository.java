package com.dropit.backend_drop_it.repositories;

import com.dropit.backend_drop_it.entities.Authority;
import com.dropit.backend_drop_it.entities.EAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, String> {
    Authority getByName(EAuthority name);
}
