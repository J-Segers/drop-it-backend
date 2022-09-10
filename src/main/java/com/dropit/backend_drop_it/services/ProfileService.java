package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.ReturnProfileDto;
import com.dropit.backend_drop_it.dtos.UpdateProfileDto;

public interface ProfileService {

    ReturnProfileDto getProfile(String id);

    ReturnProfileDto updateProfile(String id, UpdateProfileDto dto);
}
