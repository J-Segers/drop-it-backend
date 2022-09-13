package com.dropit.backend_drop_it.repositories;

import com.dropit.backend_drop_it.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> getAllBySongArtist(String artistName);
}
