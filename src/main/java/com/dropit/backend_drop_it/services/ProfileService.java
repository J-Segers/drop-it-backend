package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewProfileDto;
import com.dropit.backend_drop_it.dtos.ProfileDto;
import com.dropit.backend_drop_it.entities.RegisteredUser;

import java.util.ArrayList;

public interface ProfileService {
    ProfileDto getUser(String id);
    ArrayList<ProfileDto> getAllUsers();
    ProfileDto updateRegularUser(String id, ProfileDto userDto);
    void addNewProfile(NewProfileDto user);
    boolean checkIfUserExists(String id);
    void removeProfile(String id);
    String createNewProfileDtoFromNewUser(RegisteredUser user);
}
