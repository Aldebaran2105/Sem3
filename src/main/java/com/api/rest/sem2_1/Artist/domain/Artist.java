package com.api.rest.sem2_1.Artist.domain;

import com.api.rest.sem2_1.Album.domain.Album;
import com.api.rest.sem2_1.Song.domain.Song;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(unique = true)
    private String artistName;

    @Size(max = 1000)
    private String description;

    private Integer albumCount;

    private Integer songsCount;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Song> songs;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    public Integer getAlbumCount() {
        return this.albums != null ? this.albums.size() : 0;
    }

    public Integer getSongsCount() {
        return this.songs != null ? this.songs.size() : 0;
    }
}
