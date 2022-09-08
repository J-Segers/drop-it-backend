package com.dropit.backend_drop_it.controllers;

import com.dropit.backend_drop_it.dtos.AuthDto;
import com.dropit.backend_drop_it.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> signIn(@RequestBody AuthDto authDto) {
        String token = authService.getAuthorisation(authDto);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(token);
    }

}
