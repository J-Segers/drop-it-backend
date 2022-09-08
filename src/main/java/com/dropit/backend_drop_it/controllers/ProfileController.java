package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.dtos.ProfileDto;
import com.dropit.backend_drop_it.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("regular_users")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<ArrayList<ProfileDto>> getAllUsers() {
        return ResponseEntity.ok(profileService.getAllUsers());
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileDto> updateRegularUser(@PathVariable String id, @RequestBody ProfileDto updatedUserDto) {

        return ResponseEntity.accepted().body(profileService.updateRegularUser(id, updatedUserDto));
    }
}
