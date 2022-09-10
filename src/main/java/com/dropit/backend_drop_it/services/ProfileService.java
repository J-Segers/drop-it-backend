package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.ProfileDto;
import com.dropit.backend_drop_it.entities.Profile;

public interface ProfileService {

    ProfileDto getProfile(String id);

    ProfileDto updateProfile(String id, ProfileDto dto);
}
