package com.dropit.backend_drop_it.dtos;



public class NewProfileDto {

    private String id;
    private String username;
    private String password;
    private String registeredUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(String registeredUserId) {
        this.registeredUserId = registeredUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
