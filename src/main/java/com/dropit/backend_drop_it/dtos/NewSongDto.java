package com.dropit.backend_drop_it.dtos;


public class NewSongDto {
    Long songId;
    String songTitle;
    String songArtist;
    String songLength;
    String songGenre;
    String songCollaborators;
    String songStory;
    
    public Long getSongId() {
        return songId;
    }
    public void setSongId(Long songId) {
        this.songId = songId;
    }
    public String getSongTitle() {
        return songTitle;
    }
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
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
    public String getSongStory() {
        return songStory;
    }
    public void setSongStory(String songStory) {
        this.songStory = songStory;
    }
}