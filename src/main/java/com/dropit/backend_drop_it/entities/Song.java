package com.dropit.backend_drop_it.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long songId;

    String songTitle;
    String songArtist;
    String songLength;
    String songGenre;
    String songCollaborators;

    @OneToOne
    FileUploadResponse songImg;

    @OneToOne
    FileUploadResponse song;

    @Column(length = 2048)
    String songStory;

    public Song() {

    }

    public Song(String songTitle, String songArtist, String songLength, String songGenre, String songCollaborators, String songStory) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songLength = songLength;
        this.songGenre = songGenre;
        this.songCollaborators = songCollaborators;
        this.songStory = songStory;
    }

    public Long getSongId() {
        return songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songName) {
        this.songTitle = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getSongCollaborators() {
        return songCollaborators;
    }

    public void setSongCollaborators(String songCollaborators) {
        this.songCollaborators = songCollaborators;
    }

    public FileUploadResponse getSongImg() {
        return songImg;
    }

    public void setSongImg(FileUploadResponse songImg) {
        this.songImg = songImg;
    }

    public FileUploadResponse getSong() {
        return song;
    }

    public void setSong(FileUploadResponse song) {
        this.song = song;
    }

    public String getSongStory() {
        return songStory;
    }

    public void setSongStory(String songStory) {
        this.songStory = songStory;
    }
}
