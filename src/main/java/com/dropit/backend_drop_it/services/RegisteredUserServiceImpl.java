package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.entities.Authority;
import com.dropit.backend_drop_it.exceptions.DuplicateEmailException;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.entities.RegisteredUser;
import com.dropit.backend_drop_it.exceptions.UsernameAlreadyExistsException;
import com.dropit.backend_drop_it.repositories.AuthRepository;
import com.dropit.backend_drop_it.repositories.RegisteredUserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;
    private final ProfileService profileService;
    private final AuthRepository authRepository;

    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, ProfileService profileService, AuthRepository authRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.profileService = profileService;
        this.authRepository = authRepository;
    }

    @Override
    public RegisteredUserDto getUser(Long id) {
        checkIfUserExistsById(id);

        return convertToOutputDto(registeredUserRepository.getReferenceById(id));
    }

    @Override
    public ArrayList<RegisteredUserDto> getUserByCredentials(String username) {
        Optional<RegisteredUser> result = registeredUserRepository.findByUsername(username);
        if(result.isEmpty()) {
            throw new RecordNotFoundException("User does not exist in our database!");
        }

        ArrayList<RegisteredUserDto> list = new ArrayList<>();

        list.add(convertToOutputDto(result.get()));

        return list;
    }

    @Override
    public ArrayList<RegisteredUserDto> getAllUsers() {
        List<RegisteredUser> list = registeredUserRepository.findAll();
        ArrayList<RegisteredUserDto> outputList = new ArrayList<>();

        for (RegisteredUser user : list) {
            outputList.add(convertToOutputDto(user));
        }

        return outputList;
    }

    @Override
    public RegisteredUserDto addNewUser(NewRegisteredUserDto userDto) {
        checkIfUserAlreadyExists(userDto);

        RegisteredUser user = convertNewDtoToEntity(userDto);

        // save new user to generate id
        user = registeredUserRepository.save(user);
        System.out.println(user);
        String profileId = profileService.createNewProfileDtoFromNewUser(user);

        user.setProfileId(profileId);

        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authRepository.save(authority);

        return convertToOutputDto(registeredUserRepository.save(user));
    }

    private void checkIfUserAlreadyExists(NewRegisteredUserDto userDto) {
        Optional<RegisteredUser> result = registeredUserRepository.findByEmail(userDto.getEmail());

        if (result.isPresent()) {
            throw new DuplicateEmailException("Email already in use!");
        }

        result = registeredUserRepository.findByUsername(userDto.getUsername());

        if (result.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already taken!");
        }
    }



    @Override
    public RegisteredUserDto updateUser(Long id, RegisteredUserDto userDto) {

        RegisteredUser userToUpdate = checkIfUserExistsById(id);

        return convertToOutputDto(registeredUserRepository.save(updateUserFromDto(userToUpdate, userDto)));
    }

    @Override
    public void removeUser(Long id) {
        checkIfUserExistsById(id);
        RegisteredUser user = registeredUserRepository.getReferenceById(id);
        profileService.removeProfile(user.getProfileId());

        registeredUserRepository.deleteById(id);
    }

    private RegisteredUser checkIfUserExistsById(Long id) {
        Optional<RegisteredUser> result = registeredUserRepository.findById(id);

        if (result.isEmpty()) {
            throw new RecordNotFoundException("This user does not exist.");
        }

        return result.get();
    }

    private RegisteredUser updateUserFromDto(RegisteredUser userToUpdate, RegisteredUserDto userDto) {
        userToUpdate.setName(userDto.getName());
        userToUpdate.setUsername(userDto.getUserName());
        userToUpdate.setDob(userDto.getDob());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setLocation(userDto.getLocation());

        return userToUpdate;
    }

    private RegisteredUser convertNewDtoToEntity(NewRegisteredUserDto userDto) {
        RegisteredUser user = new RegisteredUser();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);

        return user;
    }



    private RegisteredUserDto convertToOutputDto(RegisteredUser user) {
        RegisteredUserDto userDto = new RegisteredUserDto();

        userDto.setId(user.getId());
        userDto.setProfileId(user.getProfileId());
        userDto.setArtistId(user.getArtistId());
        userDto.setProducerId(user.getProducerId());
        userDto.setName(user.getName());
        userDto.setUserName(user.getUsername());
        userDto.setDob(user.getDob());
        userDto.setEmail(user.getEmail());
        userDto.setLocation(user.getLocation());

        return userDto;
    }

}
