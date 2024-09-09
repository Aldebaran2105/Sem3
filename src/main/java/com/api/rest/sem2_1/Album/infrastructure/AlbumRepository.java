package com.api.rest.sem2_1.Album.infrastructure;

import com.api.rest.sem2_1.Album.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Long>{
    Optional<Album> findByTitle(String title);
    //Optional<List<Album>> findByArtist(String artist);
}
