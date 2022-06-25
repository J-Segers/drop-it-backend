package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.dtos.RegularUserDto;
import com.dropit.backend_drop_it.services.RegularUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("v1/regular_users")
public class RegularUserController {

    private final RegularUserService regularUserService;

    public RegularUserController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    @GetMapping("{id}")
    public ResponseEntity<RegularUserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(regularUserService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<ArrayList<RegularUserDto>> getAllUsers() {
        return ResponseEntity.ok(regularUserService.getAllUsers());
    }

    @PutMapping("{id}")
    public ResponseEntity<RegularUserDto> updateRegularUser(@PathVariable Long id, @RequestBody RegularUserDto updatedUserDto) {

        return ResponseEntity.accepted().body(regularUserService.updateRegularUser(id, updatedUserDto));
    }
}
