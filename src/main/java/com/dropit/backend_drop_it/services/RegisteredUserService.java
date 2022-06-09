package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.exceptions.DuplicateEmailException;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.models.RegisteredUser;
import com.dropit.backend_drop_it.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public RegisteredUserDto getUser(long id) {
        Optional<RegisteredUser> result = registeredUserRepository.findById(id);
        if(result.isEmpty()) {
            throw new RecordNotFoundException("User does not exist!");
        }

        return convertToOutputDto(registeredUserRepository.getReferenceById(id));
    }

    public ArrayList<RegisteredUserDto> getAllUsers() {
        List<RegisteredUser> list = registeredUserRepository.findAll();
        ArrayList<RegisteredUserDto> outputList = new ArrayList<>();

        for (RegisteredUser user : list) {
            outputList.add(convertToOutputDto(user));
        }

        return outputList;
    }

    public RegisteredUserDto addNewUser(NewRegisteredUserDto userDto) {
        Optional<RegisteredUser> result = registeredUserRepository.findByEmail(userDto.getEmail());

        if (result.isPresent()) {
            throw new DuplicateEmailException("Email already in use!");
        }

        return convertToOutputDto(registeredUserRepository.save(convertToModel(userDto)));
    }

    public RegisteredUserDto updateUser(Long id, RegisteredUserDto userDto) {
        Optional<RegisteredUser> result = registeredUserRepository.findById(id);

        if (result.isEmpty()) {
            throw new RecordNotFoundException("This user does not exist.");
        }

        RegisteredUser userToUpdate = registeredUserRepository.getReferenceById(id);

        RegisteredUser updatedUser = updateUserFromDto(userToUpdate, userDto);

        return convertToOutputDto(registeredUserRepository.save(updatedUser));
    }

    public void removeUser(Long id) {
        Optional<RegisteredUser> result = registeredUserRepository.findById(id);

        if (result.isEmpty()) {
            throw new RecordNotFoundException("This user does not exist.");
        }

        registeredUserRepository.deleteById(id);
    }

    private RegisteredUser updateUserFromDto(RegisteredUser userToUpdate, RegisteredUserDto userDto) {
        userToUpdate.setName(userDto.getName());
        userToUpdate.setUserName(userDto.getUserName());
        userToUpdate.setDob(userDto.getDob());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setLocation(userDto.getLocation());

        return userToUpdate;
    }

    private RegisteredUser convertToModel(NewRegisteredUserDto userDto) {
        RegisteredUser user = new RegisteredUser();

        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setDob(userDto.getDob());
        user.setEmail(userDto.getEmail());
        user.setLocation(userDto.getLocation());

        return user;
    }

    private RegisteredUserDto convertToOutputDto(RegisteredUser user) {
        RegisteredUserDto userDto = new RegisteredUserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUserName(user.getUserName());
        userDto.setDob(user.getDob());
        userDto.setEmail(user.getEmail());
        userDto.setLocation(user.getLocation());

        return userDto;
    }

}
