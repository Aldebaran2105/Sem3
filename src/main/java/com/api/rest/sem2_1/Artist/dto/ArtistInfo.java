package com.api.rest.sem2_1.Artist.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ArtistInfo {
    private Long id;
    private String artistName;
    private String biography;
    private List<String> album;
    private List<String> songs;
}
