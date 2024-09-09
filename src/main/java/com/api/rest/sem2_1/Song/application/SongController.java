package com.api.rest.sem2_1.Song.application;

import com.api.rest.sem2_1.Song.domain.Song;
import com.api.rest.sem2_1.Song.domain.SongService;
import com.api.rest.sem2_1.Song.dto.SongResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    public SongService songService;

    @GetMapping("/findAll")
    public ResponseEntity<List<SongResponseDTO>> findAll(){
        return ResponseEntity.ok(songService.findAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<SongResponseDTO> createSong(@RequestBody Song newSong, @PathVariable Long id){
        songService.postSong(newSong, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SongResponseDTO> findByID(@PathVariable Long id){
        return ResponseEntity.ok(songService.findById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<SongResponseDTO> findByTitle(@RequestParam String title){
        return ResponseEntity.ok(songService.findByTitle(title));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id){
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

}
