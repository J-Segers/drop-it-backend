package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewProfileDto;
import com.dropit.backend_drop_it.dtos.ProfileDto;
import com.dropit.backend_drop_it.entities.Profile;
import com.dropit.backend_drop_it.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileDto getUser(String id) {
        return convertToRegularUserDto(profileRepository.getReferenceById(id));
    }

    @Override
    public ArrayList<ProfileDto> getAllUsers() {
        return convertEntityListToDtoList(profileRepository.findAll());
    }

    @Override
    public void addNewProfile(NewProfileDto newRegularUser) {
        Profile newUser = convertNewDtoToEntity(newRegularUser);
        profileRepository.save(newUser);
    }

    @Override
    public ProfileDto updateRegularUser(String id, ProfileDto userDto) {

        return convertToRegularUserDto(profileRepository.save(convertDtoToEntity(userDto)));
    }

    @Override
    public void removeProfile(String id) {
        profileRepository.deleteById(id);
    }

    private Profile convertNewDtoToEntity(NewProfileDto dto) {
        Profile profile = new Profile();

        profile.setUsername(dto.getUsername());
        profile.setId(dto.getId());
        profile.setRegisteredUserId(dto.getRegisteredUserId());

        return profile;
    }

    private Profile convertDtoToEntity(ProfileDto userDto) {
        Profile profile = new Profile();

        profile.setUsername(userDto.getUsername());
        profile.setLikedSongs(userDto.getLikedSongs());
        profile.setDislikedSongs(userDto.getDislikedSongs());
        profile.setCompetitionsVoted(userDto.getCompetitionsVoted());

        return profile;
    }

    private ArrayList<ProfileDto> convertEntityListToDtoList(List<Profile> all) {
        ArrayList<ProfileDto> dtoList = new ArrayList<>();

        for (Profile user : all) {
            dtoList.add(convertToRegularUserDto(user));
        }

        return dtoList;
    }

    private ProfileDto convertToRegularUserDto(Profile user) {

        ProfileDto dto = new ProfileDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setLikedSongs(user.getLikedSongs());
        dto.setDislikedSongs(user.getDislikedSongs());
        dto.setCompetitionsVoted(user.getCompetitionsVoted());

        return dto;
    }

    public boolean checkIfUserExists(String id) {
        Optional<Profile> result = profileRepository.findById(id);
        return result.isPresent();
    }

}
