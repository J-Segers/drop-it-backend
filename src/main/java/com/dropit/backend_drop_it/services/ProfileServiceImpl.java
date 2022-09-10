package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.ProfileDto;
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
    public ProfileDto getProfile(String id) {
        return dtoFromProfile(profileRepository.getReferenceById(id));
    }

    @Override
    public ProfileDto updateProfile(String id, ProfileDto dto) {
        Profile profile = profileRepository.getReferenceById(id);

        profile = profileFromDto(profile, dto);

        return dtoFromProfile(profileRepository.save(profile));
    }

    private Profile profileFromDto(Profile profile, ProfileDto dto) {
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setDob(dto.getDob());
        profile.setLocation(dto.getLocation());
        profile.setStory(dto.getStory());

        return profile;
    }

    private ProfileDto dtoFromProfile(Profile profile) {
        ProfileDto dto = new ProfileDto();

        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setDob(profile.getDob());
        dto.setAge(profile.getAge());
        dto.setLocation(profile.getLocation());
        dto.setStory(profile.getStory());

        return dto;
    }

}
