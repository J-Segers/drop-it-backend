package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;


public interface RegisteredUserService {

    String addNewUser(NewRegisteredUserDto userDto);

//    RegisteredUserDto updateUser(String id, RegisteredUserDto userDto);

    void removeUser(String id);

}
