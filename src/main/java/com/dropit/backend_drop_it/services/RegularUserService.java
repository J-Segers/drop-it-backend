package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegularUserDto;
import com.dropit.backend_drop_it.dtos.RegularUserDto;

import java.util.ArrayList;

public interface RegularUserService {
    RegularUserDto getUser(long id);
    ArrayList<RegularUserDto> getAllUsers();
    RegularUserDto updateRegularUser(Long id, RegularUserDto userDto);
    void addNewRegularUser(NewRegularUserDto user);
    boolean checkIfUserExists(Long id);
    void removeRegularUser(Long id);
}
