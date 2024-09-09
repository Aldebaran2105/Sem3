package com.api.rest.sem2_1.Artist.application;

import com.api.rest.sem2_1.Artist.domain.Artist;
import com.api.rest.sem2_1.Artist.domain.ArtistService;
import com.api.rest.sem2_1.Artist.dto.AllArtist;
import com.api.rest.sem2_1.Artist.dto.ArtistInfo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistInfo> getArtistById(@PathVariable Long id){
        return ResponseEntity.ok(artistService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllArtist>> getAllArtist(){
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/name")
    public ResponseEntity<ArtistInfo> findByName(@RequestParam String name){
        return ResponseEntity.ok(artistService.findByArtistName(name));
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist newArtist){
        artistService.postArtist(newArtist);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}