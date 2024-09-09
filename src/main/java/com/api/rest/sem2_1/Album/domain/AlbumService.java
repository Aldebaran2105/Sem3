package com.api.rest.sem2_1.Album.domain;

import com.api.rest.sem2_1.Album.dto.AlbumResponseDTO;
import com.api.rest.sem2_1.Album.infrastructure.AlbumRepository;
import com.api.rest.sem2_1.Artist.domain.Artist;
import com.api.rest.sem2_1.Artist.domain.ArtistService;
import com.api.rest.sem2_1.Artist.infrastructure.ArtistRepository;
import com.api.rest.sem2_1.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    @Autowired
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ModelMapper modelMapper, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistRepository = artistRepository;

    }
    public List<AlbumResponseDTO> findAll(){
        return albumRepository.findAll().stream()
                .map(album -> modelMapper.map(album, AlbumResponseDTO.class))
                .collect(Collectors.toList());
    }
    public AlbumResponseDTO findById(Long id){
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album not found with that id"));
        AlbumResponseDTO dto = modelMapper.map(album, AlbumResponseDTO.class);
        if(album.getArtist() != null){
            dto.setArtist(album.getArtist().getArtistName());
        }
        return dto;
    }
    public AlbumResponseDTO createAlbum(Album album, Long artistId){
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        album.setArtist(artist);
        Album savedAlbum = albumRepository.save(album);
        AlbumResponseDTO dto = modelMapper.map(savedAlbum, AlbumResponseDTO.class);
        dto.setArtist(artist.getArtistName());
        return dto;
    }
    public AlbumResponseDTO findByTitle(String title){
        Album album = albumRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Album not found"));
        AlbumResponseDTO dto = modelMapper.map(album, AlbumResponseDTO.class);
        if(album.getArtist() != null){
            dto.setArtist(album.getArtist().getArtistName());
        }
        return dto;
    }

    /*
    public List<Album> findByArtistName(String artistName){
        return albumRepository.findByArtist(artistName).orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
    }
     */
    public void deleteAlbum(Long id){
        Album album = albumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Album not found with that id"));
        albumRepository.delete(album);
    }
}
