package com.dropit.backend_drop_it.dtos;

public class NewRegisteredUserDto {

    private String name;
    private String email;
    private String userName;
    private String password;
    private String dob;
    private String location;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }

    public String getLocation() {
        return location;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
