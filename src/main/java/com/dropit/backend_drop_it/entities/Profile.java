package com.dropit.backend_drop_it.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Profile {

    @Id
    private String id;

    private Long registeredUserId;

    private String username;

    private String name;
    private String location;
    private String story;

    private ArrayList<Long> likedSongs = new ArrayList<>();
    private ArrayList<Long> dislikedSongs = new ArrayList<>();
    private ArrayList<Long> competitionsVoted = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(Long registeredUserId) {
        this.registeredUserId = registeredUserId;
    }

    public ArrayList<Long> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(ArrayList<Long> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public void addSongToLikedList(Long songId) {
        likedSongs.add(songId);
    }

    public ArrayList<Long> getDislikedSongs() {
        return dislikedSongs;
    }

    public void setDislikedSongs(ArrayList<Long> dislikedSongs) {
        this.dislikedSongs = dislikedSongs;
    }

    public void addSongToDislikedList(Long songId) {
        likedSongs.add(songId);
    }

    public ArrayList<Long> getCompetitionsVoted() {
        return competitionsVoted;
    }

    public void setCompetitionsVoted(ArrayList<Long> competitionsVoted) {
        this.competitionsVoted = competitionsVoted;
    }

    public void addCompToVotedList(Long CompetitionId) {
        competitionsVoted.add(CompetitionId);
    }

}