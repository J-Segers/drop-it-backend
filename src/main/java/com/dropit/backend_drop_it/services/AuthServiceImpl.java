package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.AuthDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtServiceImpl jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public String getAuthorisation(AuthDto authDto) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtService.generateToken(userDetails);
    }


}
