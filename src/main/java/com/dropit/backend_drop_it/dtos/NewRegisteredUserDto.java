package com.dropit.backend_drop_it.dtos;

import java.time.LocalDate;
import java.util.Set;

public class NewRegisteredUserDto {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private String location;
    private String story;

    private Set<String> authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Set<String> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<String> authority) {
        this.authority = authority;
    }
}
