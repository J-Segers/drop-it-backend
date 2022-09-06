package com.dropit.backend_drop_it.repositories;

import com.dropit.backend_drop_it.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {
}
