package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.services.RegisteredUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("v1/users")
public class RegisteredUserController {

    private final RegisteredUserServiceImpl registeredUserService;

    public RegisteredUserController(RegisteredUserServiceImpl registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping("{id}")
    public ResponseEntity<RegisteredUserDto> getUser(@PathVariable long id) {
        return ResponseEntity.ok(registeredUserService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<ArrayList<RegisteredUserDto>> getAllUsers() {
        return ResponseEntity.ok(registeredUserService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<RegisteredUserDto> addNewUser(@RequestBody NewRegisteredUserDto newUserDto) {
        RegisteredUserDto user = registeredUserService.addNewUser(newUserDto);
        URI location = URI.create("" + user.getId());
        return ResponseEntity.created(location).body(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<RegisteredUserDto> updateUser(@PathVariable Long id, @RequestBody RegisteredUserDto userDto) {
        userDto = registeredUserService.updateUser(id, userDto);
        return ResponseEntity.accepted().body(userDto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable Long id) {
        registeredUserService.removeUser(id);
        return ResponseEntity.notFound().build();
    }

}
