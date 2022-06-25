package com.dropit.backend_drop_it.dtos;

import java.util.ArrayList;

public class RegularUserDto {

    private Long id;

    private ArrayList<Long> likedSongs = new ArrayList<>();
    private ArrayList<Long> dislikedSongs = new ArrayList<>();
    private ArrayList<Long> competitionsVoted = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
