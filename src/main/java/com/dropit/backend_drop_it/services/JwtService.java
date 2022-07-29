package com.dropit.backend_drop_it.services;

import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {

    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    Boolean validateToken(String token, UserDetails userDetails);

}
