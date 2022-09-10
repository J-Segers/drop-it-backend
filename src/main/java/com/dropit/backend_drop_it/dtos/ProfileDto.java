package com.dropit.backend_drop_it.dtos;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProfileDto {

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private String location;
    private String story;

    private ArrayList<Long> likedSongs = new ArrayList<>();
    private ArrayList<Long> dislikedSongs = new ArrayList<>();
    private ArrayList<Long> competitionsVoted = new ArrayList<>();

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

    public ArrayList<Long> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(ArrayList<Long> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public ArrayList<Long> getDislikedSongs() {
        return dislikedSongs;
    }

    public void setDislikedSongs(ArrayList<Long> dislikedSongs) {
        this.dislikedSongs = dislikedSongs;
    }

    public ArrayList<Long> getCompetitionsVoted() {
        return competitionsVoted;
    }

    public void setCompetitionsVoted(ArrayList<Long> competitionsVoted) {
        this.competitionsVoted = competitionsVoted;
    }
}
