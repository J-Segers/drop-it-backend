package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewRegisteredUserDto;
import com.dropit.backend_drop_it.entities.Authority;
import com.dropit.backend_drop_it.entities.EAuthority;
import com.dropit.backend_drop_it.entities.RegisteredUser;
import com.dropit.backend_drop_it.exceptions.UsernameAlreadyExistsException;
import com.dropit.backend_drop_it.repositories.AuthRepository;
import com.dropit.backend_drop_it.repositories.RegisteredUserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {


    private final RegisteredUserRepository registeredUserRepository;
    private final AuthRepository authRepository;

    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, AuthRepository authRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.authRepository = authRepository;
    }

    @Override
    public String addNewUser(NewRegisteredUserDto userDto) {
        Optional<RegisteredUser> result = registeredUserRepository.findById(userDto.getUsername());

        if (result.isPresent()) {
            throw new UsernameAlreadyExistsException("user already exists!");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        RegisteredUser user = new RegisteredUser(userDto);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Set<String> strAuthorities = userDto.getAuthority();
        Set<Authority> authorities = new HashSet<>();
        strAuthorities.forEach(authority -> {
            switch (authority) {
                case "ADMIN" -> authorities.add(authRepository.getByName(EAuthority.ROLE_ADMIN));
                case "PRODUCER" -> authorities.add(authRepository.getByName(EAuthority.ROLE_PRODUCER));
                case "ARTIST" -> authorities.add(authRepository.getByName(EAuthority.ROLE_ARTIST));
                case "USER" -> authorities.add(authRepository.getByName(EAuthority.ROLE_USER));
            }
        });
        user.setAuthorities(authorities);
        registeredUserRepository.save(user);

        return "Success" ;
    }

    @Override
    public void removeUser(String id) {
        registeredUserRepository.deleteById(id);
    }

}