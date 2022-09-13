package com.dropit.backend_drop_it.controllers;


import com.dropit.backend_drop_it.dtos.NewSongDto;
import com.dropit.backend_drop_it.entities.FileUploadResponse;
import com.dropit.backend_drop_it.entities.Song;
import com.dropit.backend_drop_it.services.SongService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{artistName}/songs")
    public ResponseEntity<List<Song>> getAllSongsOfId(@PathVariable String artistName) {
        return ResponseEntity.ok(songService.getAllSongsOfArtist(artistName));
    }

    @GetMapping("allsongs")
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @PostMapping("songUpload/newSong")
    public ResponseEntity<Song> addNewSong(@RequestBody NewSongDto songDto) {

        Song song = songService.addSong(songDto);
        URI location = URI.create(song.getSongTitle());

        return ResponseEntity.created(location).body(song);
    }

    //    post for single upload
    @PostMapping("/songUpload/{type}/{id}")
    public ResponseEntity<FileUploadResponse> singleFileUpload(@PathVariable String type, @PathVariable Long id, @RequestParam("file") MultipartFile file){

        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/song/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        FileUploadResponse songFile = songService.storeFile(file, url);
        songService.assignFileToSong(songFile.getFileName(), id, type);

        return ResponseEntity.ok().body(songFile);
    }

    //    get for single download
    @GetMapping("/download/song/{fileName}")
    public ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = songService.downLoadFile(fileName);

//        this mediaType decides witch type you accept if you only accept 1 type
//        MediaType contentType = MediaType.IMAGE_JPEG;
//        this is going to accept multiple types
        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

//        for download attachment use next line
//        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);
//        for showing image in browser
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

}
