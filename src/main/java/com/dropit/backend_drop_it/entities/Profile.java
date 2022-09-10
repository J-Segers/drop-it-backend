package com.dropit.backend_drop_it.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Profile {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private String location;

    @Column(length = 2048)
    private String story;

//    private Set<Long> likedSongs = new HashSet<>();
//    private Set<Long> dislikedSongs = new HashSet<>();
//    private Set<Long> competitionsVoted = new HashSet<>();


    public Profile() {
    }

    public Profile(String username, String email) {
        this.username = username;
        this.email = email;
    }

//    public Profile(String username, String email, String firstName, String lastName, LocalDate dob, String location, String story) {
//        this.username = username;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dob = dob;
//        this.age = Period.between(dob, LocalDate.now()).getYears();
//        this.location = location;
//        this.story = story;
//    }

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

//    public Set<Long> getLikedSongs() {
//        return likedSongs;
//    }
//
//    public void setLikedSongs(Set<Long> likedSongs) {
//        this.likedSongs = likedSongs;
//    }
//
//    public Set<Long> getDislikedSongs() {
//        return dislikedSongs;
//    }
//
//    public void setDislikedSongs(Set<Long> dislikedSongs) {
//        this.dislikedSongs = dislikedSongs;
//    }
//
//    public Set<Long> getCompetitionsVoted() {
//        return competitionsVoted;
//    }
//
//    public void setCompetitionsVoted(Set<Long> competitionsVoted) {
//        this.competitionsVoted = competitionsVoted;
//    }
}