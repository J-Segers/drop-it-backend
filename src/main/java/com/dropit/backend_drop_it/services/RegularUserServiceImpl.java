package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegularUserDto;
import com.dropit.backend_drop_it.dtos.RegularUserDto;
import com.dropit.backend_drop_it.entities.RegularUser;
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

    @Override
    public void addNewRegularUser(NewRegularUserDto newRegularUser) {
        RegularUser newUser = convertNewDtoToEntity(newRegularUser);
        regularUserRepository.save(newUser);
    }

    @Override
    public RegularUserDto updateRegularUser(Long id, RegularUserDto userDto) {

        return convertToRegularUserDto(regularUserRepository.save(convertDtoToEntity(userDto)));
    }

    @Override
    public void removeRegularUser(Long id) {
        regularUserRepository.deleteById(id);
    }

    private RegularUser convertNewDtoToEntity(NewRegularUserDto dto) {
        RegularUser regularUser = new RegularUser();

        regularUser.setUsername(dto.getUsername());
        regularUser.setId(dto.getId());
        regularUser.setRegisteredUserId(dto.getRegisteredUserId());

        return regularUser;
    }

    private RegularUser convertDtoToEntity(RegularUserDto userDto) {
        RegularUser regularUser = new RegularUser();

        regularUser.setUsername(userDto.getUsername());
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
        dto.setUsername(user.getUsername());
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
