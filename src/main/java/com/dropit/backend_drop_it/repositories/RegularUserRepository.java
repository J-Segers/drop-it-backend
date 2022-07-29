package com.dropit.backend_drop_it.repositories;

import com.dropit.backend_drop_it.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {
}
