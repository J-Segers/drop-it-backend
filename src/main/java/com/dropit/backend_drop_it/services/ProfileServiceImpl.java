package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.ReturnProfileDto;
import com.dropit.backend_drop_it.dtos.UpdateProfileDto;
import com.dropit.backend_drop_it.entities.Profile;
import com.dropit.backend_drop_it.repositories.ProfileRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ReturnProfileDto getProfile(String id) {
        return dtoFromProfile(profileRepository.getReferenceById(id));
    }

    @Override
    public ReturnProfileDto updateProfile(String id, UpdateProfileDto dto) {
        Profile profile = profileRepository.getReferenceById(id);

        profileFromDto(profile, dto);

        profile = profileRepository.save(profile);


        return dtoFromProfile(profile);
    }

    private Profile profileFromDto(Profile profile, UpdateProfileDto dto) {

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setDob(dto.getDob());
        profile.setLocation(dto.getLocation());
        profile.setStory(dto.getStory());

        return profile;
    }

    private ReturnProfileDto dtoFromProfile(Profile profile) {
        ReturnProfileDto dto = new ReturnProfileDto();

        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setAge(profile.getAge());
        dto.setLocation(profile.getLocation());
        dto.setStory(profile.getStory());
        dto.setProfileImg(profile.getProfileImg());
        dto.setProfileBodyImg(profile.getProfileBodyImg());

        return dto;
    }

}
