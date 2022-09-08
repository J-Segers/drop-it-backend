package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.entities.Profile;
import com.dropit.backend_drop_it.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Profile> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }

}
