package com.dropit.backend_drop_it.dtos;



public class NewRegularUserDto {

    private Long id;
    private String username;
    private String password;
    private Long registeredUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(Long registerdUserId) {
        this.registeredUserId = registerdUserId;
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

}
