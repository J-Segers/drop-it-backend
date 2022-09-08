package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.dtos.RegisteredUserDto;
import com.dropit.backend_drop_it.services.RegisteredUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

//    @GetMapping("{id}")
//    public ResponseEntity<RegisteredUserDto> getUser(@PathVariable String id) {
//        return ResponseEntity.ok(registeredUserService.getUser(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<ArrayList<RegisteredUserDto>> getAllUsers(@RequestParam(required = false) String username) {
//
//        if(!username.isEmpty()) {
//                return ResponseEntity.ok(registeredUserService.getUserByCredentials(username));
//        } else {
//            return ResponseEntity.ok(registeredUserService.getAllUsers());
//        }
//
//    }

    @PostMapping
    public ResponseEntity<String> addNewUser(@RequestBody NewRegisteredUserDto newUserDto) {
        String user = registeredUserService.addNewUser(newUserDto);
        URI location = URI.create(user);
        return ResponseEntity.created(location).body(user);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<RegisteredUserDto> updateUser(@PathVariable String id, @RequestBody RegisteredUserDto userDto) {
//        userDto = registeredUserService.updateUser(id, userDto);
//        return ResponseEntity.accepted().body(userDto);
//    }
//
//
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> removeUser(@RequestParam String id) {
        registeredUserService.removeUser(id);
        return ResponseEntity.notFound().build();
    }

}
