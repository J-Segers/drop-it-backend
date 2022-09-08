package com.dropit.backend_drop_it.services;

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
    public Profile getProfile(String id) {
        return profileRepository.getReferenceById(id);
    }

}
