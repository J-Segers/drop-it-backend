package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.exceptions.DuplicateEmailException;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.models.RegisteredUser;
import com.dropit.backend_drop_it.models.RegularUser;
import com.dropit.backend_drop_it.repositories.RegisteredUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;
    private final RegularUserService regularUserService;

    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, RegularUserService regularUserService) {
        this.registeredUserRepository = registeredUserRepository;
        this.regularUserService = regularUserService;
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

    public RegisteredUserDto addNewUser(NewRegisteredUserDto userDto) {
        Optional<RegisteredUser> result = registeredUserRepository.findByEmail(userDto.getEmail());

        if (result.isPresent()) {
            throw new DuplicateEmailException("Email already in use!");
        }

        RegisteredUser user = convertNewDtoToEntity(userDto);

        //save to generate userId so we can add it later to the regular user.
        user = registeredUserRepository.save(user);

        RegularUser regularUser = new RegularUser();

        regularUser.setId(generateRandomSequence());
        user.setRegularUserId(regularUser.getId());
        regularUser.setRegisteredUserId(user.getId());

        regularUserService.addNewRegularUser(regularUser);

        return convertToOutputDto(registeredUserRepository.save(user));
    }

    public RegisteredUserDto updateUser(Long id, RegisteredUserDto userDto) {
        checkIfUserExistsById(id);

        RegisteredUser userToUpdate = registeredUserRepository.getReferenceById(id);

        return convertToOutputDto(registeredUserRepository.save(updateUserFromDto(userToUpdate, userDto)));
    }

    public void removeUser(Long id) {
        checkIfUserExistsById(id);
        RegisteredUser user = registeredUserRepository.getReferenceById(id);
        regularUserService.removeRegularUser(user.getId());
        if(user.isArtist()) {
            //remove from artistRepository
        }
        if (user.isProducer()) {
            //remove from producerRepository
        }
        if (user.isAdmin()) {
            //remove from adminRepository
        }
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
        userToUpdate.setUserName(userDto.getUserName());
        userToUpdate.setDob(userDto.getDob());
        userToUpdate.setEmail(userDto.getEmail());
        userToUpdate.setLocation(userDto.getLocation());

        return userToUpdate;
    }

    private RegisteredUser convertNewDtoToEntity(NewRegisteredUserDto userDto) {
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
        userDto.setRegularUserId(user.getRegularUserId());
        userDto.setArtistId(user.getArtistId());
        userDto.setProducerId(user.getProducerId());
        userDto.setName(user.getName());
        userDto.setUserName(user.getUserName());
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
