package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.AuthDto;

public interface AuthService {
    String getAuthorisation(AuthDto authDto);
}
