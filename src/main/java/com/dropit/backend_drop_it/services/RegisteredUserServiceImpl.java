package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.NewRegularUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.entities.Authority;
import com.dropit.backend_drop_it.exceptions.DuplicateEmailException;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.entities.RegisteredUser;
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
    private final RegularUserService regularUserService;
    private final AuthRepository authRepository;

    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, RegularUserService regularUserService, AuthRepository authRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.regularUserService = regularUserService;
        this.authRepository = authRepository;
    }

    @Override
    public RegisteredUserDto getUser(long id) {
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
        Optional<RegisteredUser> result = registeredUserRepository.findByEmail(userDto.getEmail());

        if (result.isPresent()) {
            throw new DuplicateEmailException("Email already in use!");
        }

        RegisteredUser user = convertNewDtoToEntity(userDto);

        //save to generate userId so we can add it later to the regular user.
        user = registeredUserRepository.save(user);

        NewRegularUserDto newRegularUser = createNewRegularUserDto(user);

        user.setRegularUserId(newRegularUser.getId());

        Authority authority = new Authority();
        authority.setUsername(userDto.getUserName());
        authority.setAuthority("USER");
        authRepository.save(authority);

        regularUserService.addNewRegularUser(newRegularUser);

        return convertToOutputDto(registeredUserRepository.save(user));
    }

    private NewRegularUserDto createNewRegularUserDto(RegisteredUser user) {
        NewRegularUserDto regularUser = new NewRegularUserDto();

        regularUser.setId(generateRandomSequence());
        regularUser.setRegisteredUserId(user.getId());
        regularUser.setUsername(user.getUsername());

        return regularUser;
    }

    @Override
    public RegisteredUserDto updateUser(Long id, RegisteredUserDto userDto) {
        checkIfUserExistsById(id);

        RegisteredUser userToUpdate = registeredUserRepository.getReferenceById(id);

        return convertToOutputDto(registeredUserRepository.save(updateUserFromDto(userToUpdate, userDto)));
    }

    @Override
    public void removeUser(Long id) {
        checkIfUserExistsById(id);
        RegisteredUser user = registeredUserRepository.getReferenceById(id);
        regularUserService.removeRegularUser(user.getId());

        registeredUserRepository.deleteById(id);
    }

    private void checkIfUserExistsById(Long id) {
        Optional<RegisteredUser> result = registeredUserRepository.findById(id);

        if (result.isEmpty()) {
            throw new RecordNotFoundException("This user does not exist.");
        }
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

        user.setName(userDto.getName());
        user.setUsername(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(userDto.isEnabled());
        user.setDob(userDto.getDob());
        user.setEmail(userDto.getEmail());
        user.setLocation(userDto.getLocation());

        return user;
    }

    private RegisteredUserDto convertToOutputDto(RegisteredUser user) {
        RegisteredUserDto userDto = new RegisteredUserDto();

        userDto.setId(user.getId());
        userDto.setRegularUserId(user.getRegularUserId());
        userDto.setArtistId(user.getArtistId());
        userDto.setProducerId(user.getProducerId());
        userDto.setName(user.getName());
        userDto.setUserName(user.getUsername());
        userDto.setDob(user.getDob());
        userDto.setEmail(user.getEmail());
        userDto.setLocation(user.getLocation());

        return userDto;
    }

    private long generateRandomSequence() {
        final long MAX_SIZE = 10_000_000_000L;
        final long MIN_SIZE = 999_999_999L;
        int length = 10;
        long generatedSequence = 0L;

        while (true) {
            for (int i = 0; i < length; i++) {
                generatedSequence += Math.random() * 100 / 10;

                if(i < length - 1){
                    generatedSequence *= 10;
                }
            }
            boolean result = regularUserService.checkIfUserExists(generatedSequence);

            if((generatedSequence < MAX_SIZE && generatedSequence > MIN_SIZE) && !result) {
                break;
            }
        }

        return generatedSequence;
    }

}
