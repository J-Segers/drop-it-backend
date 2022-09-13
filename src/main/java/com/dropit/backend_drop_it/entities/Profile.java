package com.dropit.backend_drop_it.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Profile {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private String location;

    @OneToOne
    private FileUploadResponse profileImg;

    @OneToOne
    private FileUploadResponse profileBodyImg;

    @Column(length = 2048)
    private String story;

//    private Set<Long> likedSongs = new HashSet<>();
//    private Set<Long> dislikedSongs = new HashSet<>();
//    private Set<Long> competitionsVoted = new HashSet<>();


    public Profile() {
    }

    public Profile(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public FileUploadResponse getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(FileUploadResponse profileImg) {
        this.profileImg = profileImg;
    }

    public FileUploadResponse getProfileBodyImg() {
        return profileBodyImg;
    }

    public void setProfileBodyImg(FileUploadResponse profileBodyImg) {
        this.profileBodyImg = profileBodyImg;
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