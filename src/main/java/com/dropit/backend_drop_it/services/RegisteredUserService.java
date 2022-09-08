package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.AuthDto;
import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;

import java.util.ArrayList;

public interface RegisteredUserService {

    RegisteredUserDto getUser(Long id);

    ArrayList<RegisteredUserDto> getAllUsers();

    RegisteredUserDto addNewUser(NewRegisteredUserDto userDto);

    RegisteredUserDto updateUser(Long id, RegisteredUserDto userDto);

    void removeUser(Long id);

    ArrayList<RegisteredUserDto> getUserByCredentials(String username);
}
