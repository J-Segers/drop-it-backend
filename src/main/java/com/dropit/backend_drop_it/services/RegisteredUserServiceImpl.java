package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.NewProfileDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.entities.Authority;
import com.dropit.backend_drop_it.exceptions.DuplicateEmailException;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.entities.RegisteredUser;
import com.dropit.backend_drop_it.exceptions.UsernameAlreadyExistsException;
import com.dropit.backend_drop_it.repositories.AuthRepository;
import com.dropit.backend_drop_it.repositories.RegisteredUserRepository;
import com.dropit.backend_drop_it.util.SequenceGenerator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    private final int idLength = 10;

    private final SequenceGenerator sequenceGenerator = new SequenceGenerator();
    private final RegisteredUserRepository registeredUserRepository;
    private final ProfileService profileService;
    private final AuthRepository authRepository;

    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, ProfileService profileService, AuthRepository authRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.profileService = profileService;
        this.authRepository = authRepository;
    }

    @Override
    public ArrayList<RegisteredUserDto> getUserByCredentials(String username) {
        ArrayList<RegisteredUserDto> list = new ArrayList<>();
        list.add(convertToOutputDto(registeredUserRepository.getReferenceByUsername(username)));
        return list;
    }

    @Override
    public RegisteredUserDto getUser(String id) {
        checkIfUserExistsById(id);

        return convertToOutputDto(registeredUserRepository.getReferenceById(id));
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

        NewProfileDto newProfile = createNewProfileDto(user);

        user.setProfileId(newProfile.getId());

        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authRepository.save(authority);

        profileService.addNewProfile(newProfile);

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

    private NewProfileDto createNewProfileDto(RegisteredUser user) {
        NewProfileDto newProfile = new NewProfileDto();
        String newId;

        newProfile.setId(generateID(idLength));
        newProfile.setRegisteredUserId(user.getId());
        newProfile.setUsername(user.getUsername());

        return newProfile;

    }

    @Override
    public RegisteredUserDto updateUser(String id, RegisteredUserDto userDto) {

        RegisteredUser userToUpdate = checkIfUserExistsById(id);

        return convertToOutputDto(registeredUserRepository.save(updateUserFromDto(userToUpdate, userDto)));
    }

    @Override
    public void removeUser(String id) {
        checkIfUserExistsById(id);
        RegisteredUser user = registeredUserRepository.getReferenceById(id);
        profileService.removeProfile(user.getProfileId());

        registeredUserRepository.deleteById(id);
    }

    private RegisteredUser checkIfUserExistsById(String id) {
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

        user.setId(generateID(idLength));
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);

        return user;
    }

    private String generateID(int length){
        String newId;
        do {
            newId = sequenceGenerator.AlphaNumeric(length);
        } while (registeredUserRepository.findById(newId).isPresent());
        return newId;
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
