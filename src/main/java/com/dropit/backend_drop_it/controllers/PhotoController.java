package com.dropit.backend_drop_it.controllers;


import com.dropit.backend_drop_it.entities.FileUploadResponse;
import com.dropit.backend_drop_it.services.PhotoService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin
public class PhotoController {
    private final PhotoService photoService;


    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    //    post for single upload
    @PostMapping("/upload/{type}/{id}")
    public ResponseEntity<FileUploadResponse> singleFileUpload(@PathVariable String type, @PathVariable String id, @RequestParam("file") MultipartFile file){

        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/profile/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        FileUploadResponse photo = photoService.storeFile(file, url);

        photoService.assignPhotoToProfile(photo.getFileName(), id, type);

        return ResponseEntity.ok().body(photo);
    }

    //    get for single download
    @GetMapping("/download/profile/{fileName}")
    public ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = photoService.downLoadFile(fileName);

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
