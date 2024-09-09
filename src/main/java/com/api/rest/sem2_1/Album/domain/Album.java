package com.api.rest.sem2_1.Album.domain;

import com.api.rest.sem2_1.Artist.domain.Artist;
import com.api.rest.sem2_1.Song.domain.Song;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 1, max = 40)
    //@Column(unique = true)
    private String title;

    @Size(max = 500)
    private String description;

    private Integer songsCount = 0;

    @NotNull
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Song> songs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "artist_id")
    private Artist artist;


    public void incrementSongsCount(){
        this.songsCount++;
    }

    public void decrementSongsCount(){
        if(this.songsCount > 0){
            this.songsCount--;
        }
    }
}
