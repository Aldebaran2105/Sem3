package com.api.rest.sem2_1.Artist.dto;

import lombok.Data;

@Data
public class AllArtist {
    private Long id;
    private String artistName;
    private Integer albumCount;
    private Integer songCount;
}
