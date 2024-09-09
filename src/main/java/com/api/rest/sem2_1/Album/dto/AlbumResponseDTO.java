package com.api.rest.sem2_1.Album.dto;

import lombok.Data;

@Data
public class AlbumResponseDTO {
    private Long id;
    private String title;
    private Integer songsCount;
    private String artist;
}
