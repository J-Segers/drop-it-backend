package com.dropit.backend_drop_it.dtos;

import java.util.ArrayList;

public class ProfileDto {

    private String id;

    private String username;

    private boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
