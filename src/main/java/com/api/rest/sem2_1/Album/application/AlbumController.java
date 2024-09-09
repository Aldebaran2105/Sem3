package com.api.rest.sem2_1.Album.application;

import com.api.rest.sem2_1.Album.domain.Album;
import com.api.rest.sem2_1.Album.domain.AlbumService;
import com.api.rest.sem2_1.Album.dto.AlbumResponseDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlbumResponseDTO>> findAll(){
        return ResponseEntity.ok(albumService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<AlbumResponseDTO> getArtistByID(@PathVariable Long id){
        return ResponseEntity.ok(albumService.findById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> createAlbum(@RequestBody Album newAlbum, @PathVariable Long id){
        albumService.createAlbum(newAlbum, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/title")
    public ResponseEntity<AlbumResponseDTO> getAlbumByTitle(@RequestParam String title){
        return ResponseEntity.ok(albumService.findByTitle(title));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
