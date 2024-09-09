package com.api.rest.sem2_1.Song.domain;

import com.api.rest.sem2_1.Album.domain.Album;
import com.api.rest.sem2_1.Album.infrastructure.AlbumRepository;
import com.api.rest.sem2_1.Song.dto.SongResponseDTO;
import com.api.rest.sem2_1.Song.repository.SongRepository;
import com.api.rest.sem2_1.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {
    @Autowired
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final AlbumRepository albumRepository;

    public SongService(ModelMapper modelMapper, AlbumRepository albumRepository, SongRepository songRepository) {
        this.modelMapper = modelMapper;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public List<SongResponseDTO> findAll(){
        return songRepository.findAll().stream()
                .map(song -> modelMapper.map(song, SongResponseDTO.class))
                .collect(Collectors.toList());
    }

    public SongResponseDTO findById(Long id){
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        SongResponseDTO dto = modelMapper.map(song, SongResponseDTO.class);
        if(song.getAlbum() != null && song.getArtist() != null){
            dto.setAlbumTitle(song.getAlbum().getTitle());
            dto.setArtistName(song.getArtist().getArtistName());
        }
        return dto;
    }

    public SongResponseDTO findByTitle(String title){
        Song song = songRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        SongResponseDTO dto = modelMapper.map(song, SongResponseDTO.class);
        if(song.getAlbum() != null && song.getArtist() != null){
            dto.setAlbumTitle(song.getAlbum().getTitle());
            dto.setArtistName(song.getArtist().getArtistName());
        }
        return dto;
    }

    public SongResponseDTO postSong(Song song, Long albumId){
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new ResourceNotFoundException("Album not found"));
        song.setAlbum(album);
        song.setArtist(album.getArtist());
        album.incrementSongsCount();
        Song savedSong = songRepository.save(song);
        SongResponseDTO dto = modelMapper.map(savedSong, SongResponseDTO.class);
        dto.setAlbumTitle(song.getAlbum().getTitle());
        dto.setArtistName(song.getArtist().getArtistName());
        return dto;
    }

    public void deleteSong(Long id){
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song not found"));
        Album album = song.getAlbum();
        songRepository.delete(song);
        album.decrementSongsCount();
        albumRepository.save(album);
    }

    /*
    Agregar find by artist name
    Agregar find by albym
     */
}
