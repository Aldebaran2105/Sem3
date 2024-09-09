package com.api.rest.sem2_1.Artist.domain;

import com.api.rest.sem2_1.Album.domain.Album;
import com.api.rest.sem2_1.Album.infrastructure.AlbumRepository;
import com.api.rest.sem2_1.Artist.dto.AllArtist;
import com.api.rest.sem2_1.Artist.dto.ArtistInfo;
import com.api.rest.sem2_1.Artist.infrastructure.ArtistRepository;
import com.api.rest.sem2_1.Song.domain.Song;
import com.api.rest.sem2_1.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArtistService {
    @Autowired
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    public ArtistService(ArtistRepository artistRepository, ModelMapper modelMapper){
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }


    public List<AllArtist> findAll(){
        return artistRepository.findAll().stream()
                .map(artist -> {
                    AllArtist dto = modelMapper.map(artist, AllArtist.class);
                    dto.setAlbumCount(artist.getSongsCount());
                    dto.setSongCount(artist.getSongsCount());
                    return dto;
                }).collect(Collectors.toList());

    }

    public ArtistInfo findById(Long id){
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist not found with the id: " + id));
        ArtistInfo dto = modelMapper.map(artist, ArtistInfo.class);

        List<String> albumTitles = artist.getAlbums().stream()
                .map(Album::getTitle)
                .collect(Collectors.toList());

        List<String> songTitles = artist.getSongs().stream()
                .map(Song::getTitle)
                .collect(Collectors.toList());

        dto.setAlbum(albumTitles);
        dto.setSongs(songTitles);
        dto.setBiography(artist.getDescription());
        return dto;
    }

    public ArtistInfo findByArtistName(String artistName){
        Artist artist = artistRepository.findByArtistName(artistName).orElseThrow(() -> new ResourceNotFoundException("Artist not found"));
        ArtistInfo dto = modelMapper.map(artist, ArtistInfo.class);

        List<String> albumTitles = artist.getAlbums().stream()
                .map(Album::getTitle)
                .collect(Collectors.toList());

        List<String> songTitles = artist.getSongs().stream()
                .map(Song::getTitle)
                .collect(Collectors.toList());

        dto.setAlbum(albumTitles);
        dto.setSongs(songTitles);
        dto.setBiography(artist.getDescription());
        return dto;
    }

    public Artist postArtist(Artist artist){
        Artist artist1 = new Artist();
        artist1.setArtistName(artist.getArtistName());
        artist1.setDescription(artist.getDescription());
        artistRepository.save(artist1);
        return artist1;
    }


    public void deleteArtist(Long id){
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist not found with the id: " + id));
        artistRepository.delete(artist);
    }
}
