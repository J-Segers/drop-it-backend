package com.dropit.backend_drop_it.models;

import javax.persistence.*;

@Entity
public class RegisteredUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regularUserId;
    private Long artistId;
    private Long producerId;

    private String name;
    private String email;
    private String userName;
    private String dob;
    private String location;

    //roles
    private boolean isRegularUser = true;
    private boolean isAdmin = false;
    private boolean isArtist = false;
    private boolean isProducer = false;

    public Long getId() {
        return id;
    }

    public Long getRegularUserId() {
        return regularUserId;
    }

    public void setRegularUserId(Long regularUserId) {
        this.regularUserId = regularUserId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRegularUser() {
        return isRegularUser;
    }

    public void setRegularUser(boolean regularUser) {
        isRegularUser = regularUser;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public void setArtist(boolean artist) {
        isArtist = artist;
    }

    public boolean isProducer() {
        return isProducer;
    }

    public void setProducer(boolean producer) {
        isProducer = producer;
    }

}
