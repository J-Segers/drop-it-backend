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

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public String getAuthorisation(AuthDto authDto) {
        System.out.println(authDto);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        System.out.println(authToken);

        System.out.println(authToken);
        Authentication authentication = authenticationManager.authenticate(authToken);
        System.out.println(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails);
        return jwtService.generateToken(userDetails);
    }


}
