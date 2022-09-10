package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.entities.FileUploadResponse;
import com.dropit.backend_drop_it.entities.Profile;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.repositories.FileUploadRepository;
import com.dropit.backend_drop_it.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class PhotoService {
    @Value("${my.upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;

    private final FileUploadRepository fileUploadRepository;

    private final ProfileRepository profileRepository;

    public PhotoService(@Value("${my.upload_location}") String fileStorageLocation, FileUploadRepository fileUploadRepository, ProfileRepository profileRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        this.fileStorageLocation = fileStorageLocation;
        this.fileUploadRepository = fileUploadRepository;

        this.profileRepository = profileRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }

    }

    public FileUploadResponse storeFile(MultipartFile file, String url) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Issue in storing the file", e);
        }

        return fileUploadRepository.save(new FileUploadResponse(fileName, file.getContentType(), url));

    }

    public Resource downLoadFile(String fileName) {

        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if(resource.exists()&& resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }

    public void assignPhotoToProfile(String fileName, String username, String type) {
        Optional<Profile> optionalProfile = profileRepository.findById(username);
        Optional<FileUploadResponse> photoUploadResponse = fileUploadRepository.findByFileName(fileName);
        if(optionalProfile.isPresent() && photoUploadResponse.isPresent()) {
            FileUploadResponse photo = photoUploadResponse.get();
            Profile profile = optionalProfile.get();
            if(type.equals("profileImg")) {
                profile.setProfileImg(photo);
            } else if(type.equals("profileBody")) {
                profile.setProfileBodyImg(photo);
            }
            profile.setProfileImg(photo);
            profileRepository.save(profile);
        } else {
            throw new RecordNotFoundException("Gebruiker: " + username + " niet gevonden");
        }

    }

}

