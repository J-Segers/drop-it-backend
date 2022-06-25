package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.RegularUserDto;
import com.dropit.backend_drop_it.models.RegularUser;
import com.dropit.backend_drop_it.repositories.RegularUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegularUserServiceImpl implements RegularUserService {

    private final RegularUserRepository regularUserRepository;

    public RegularUserServiceImpl(RegularUserRepository regularUserRepository) {
        this.regularUserRepository = regularUserRepository;
    }

    @Override
    public RegularUserDto getUser(long id) {
        return convertToRegularUserDto(regularUserRepository.getReferenceById(id));
    }

    @Override
    public ArrayList<RegularUserDto> getAllUsers() {
        return convertEntityListToDtoList(regularUserRepository.findAll());
    }

    public void addNewRegularUser(RegularUser user) {
        regularUserRepository.save(user);
    }

    @Override
    public RegularUserDto updateRegularUser(Long id, RegularUserDto userDto) {

        return convertToRegularUserDto(regularUserRepository.save(convertDtoToEntity(userDto)));
    }

    @Override
    public void removeRegularUser(Long id) {
        regularUserRepository.deleteById(id);
    }

    private RegularUser convertDtoToEntity(RegularUserDto userDto) {
        RegularUser regularUser = new RegularUser();

        regularUser.setLikedSongs(userDto.getLikedSongs());
        regularUser.setDislikedSongs(userDto.getDislikedSongs());
        regularUser.setCompetitionsVoted(userDto.getCompetitionsVoted());

        return regularUser;
    }

    private ArrayList<RegularUserDto> convertEntityListToDtoList(List<RegularUser> all) {
        ArrayList<RegularUserDto> dtoList = new ArrayList<>();

        for (RegularUser user : all) {
            dtoList.add(convertToRegularUserDto(user));
        }

        return dtoList;
    }

    private RegularUserDto convertToRegularUserDto(RegularUser user) {

        RegularUserDto dto = new RegularUserDto();

        dto.setId(user.getId());
        dto.setLikedSongs(user.getLikedSongs());
        dto.setDislikedSongs(user.getDislikedSongs());
        dto.setCompetitionsVoted(user.getCompetitionsVoted());

        return dto;
    }

    public boolean checkIfUserExists(Long id) {
        Optional<RegularUser> result = regularUserRepository.findById(id);
        return result.isPresent();
    }

}
