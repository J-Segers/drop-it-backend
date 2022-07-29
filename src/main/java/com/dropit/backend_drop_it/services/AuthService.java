package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.AuthDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    String getAuthorisation(AuthDto authDto);
}
