package com.dropit.backend_drop_it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;

@Entity
public class Song {

    @Id
    String songId;

    String songTitle;
    String songArtist;
    String songLength;
    String songGenre;
    String songCollaborators;
    File songImg;
    File song;

    @Column(length = 2048)
    String songStory;

    public Song() {

    }

    public Song(String songId, String songTitle, String songArtist, String songLength, String songGenre, String songCollaborators, File songImg, File song, String songStory) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songLength = songLength;
        this.songGenre = songGenre;
        this.songCollaborators = songCollaborators;
        this.songImg = songImg;
        this.song = song;
        this.songStory = songStory;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
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

    public File getSongImg() {
        return songImg;
    }

    public void setSongImg(File songImg) {
        this.songImg = songImg;
    }

    public File getSong() {
        return song;
    }

    public void setSong(File song) {
        this.song = song;
    }

    public String getSongStory() {
        return songStory;
    }

    public void setSongStory(String songStory) {
        this.songStory = songStory;
    }
}
