package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.dtos.ProfileDto;
import com.dropit.backend_drop_it.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable String id, @RequestBody ProfileDto dto) {
        return ResponseEntity.ok(profileService.updateProfile(id, dto));
    }


}
