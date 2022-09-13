package com.dropit.backend_drop_it.services;

import com.dropit.backend_drop_it.dtos.NewSongDto;
import com.dropit.backend_drop_it.entities.FileUploadResponse;
import com.dropit.backend_drop_it.entities.Song;
import com.dropit.backend_drop_it.exceptions.RecordNotFoundException;
import com.dropit.backend_drop_it.repositories.FileUploadRepository;
import com.dropit.backend_drop_it.repositories.SongRepository;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SongService {
    @Value("${my.song_upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;

    private final FileUploadRepository fileUploadRepository;

    private final SongRepository songRepository;

    public SongService(@Value("${my.song_upload_location}") String fileStorageLocation, FileUploadRepository fileUploadRepository, SongRepository songRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        this.fileStorageLocation = fileStorageLocation;
        this.fileUploadRepository = fileUploadRepository;

        this.songRepository = songRepository;

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

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }

    public void assignFileToSong(String fileName, Long songId, String type) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        Optional<FileUploadResponse> songUploadResponse = fileUploadRepository.findByFileName(fileName);

        if (optionalSong.isPresent() && songUploadResponse.isPresent()) {
            FileUploadResponse songFile = songUploadResponse.get();
            Song song = optionalSong.get();

            if (type.equals("img")) {
                song.setSongImg(songFile);
            } else if (type.equals("song")) {
                song.setSong(songFile);
            }

            songRepository.save(song);
        } else {
            throw new RecordNotFoundException("Song with id: " + songId + " does not exist!");
        }

    }

    public Song addSong(NewSongDto songDto) {
        Song song = new Song(
                songDto.getSongTitle(),
                songDto.getSongArtist(),
                songDto.getSongLength(),
                songDto.getSongGenre(),
                songDto.getSongCollaborators(),
                songDto.getSongStory()
        );
        return songRepository.save(song);
    }

    public List<Song> getAllSongsOfArtist(String artistName) {
        return songRepository.getAllBySongArtist(artistName);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}

