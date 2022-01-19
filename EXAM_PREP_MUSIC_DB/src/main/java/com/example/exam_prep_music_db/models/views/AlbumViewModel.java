package com.example.exam_prep_music_db.models.views;

import com.example.exam_prep_music_db.models.entities.enums.ArtistNameEnum;
import com.example.exam_prep_music_db.models.entities.enums.GenreEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AlbumViewModel {

    private Long id;
    private String name;
    private ArtistNameEnum artist;
    private GenreEnum genre;
    private BigDecimal price;
    private LocalDate releaseDate;

    public AlbumViewModel() {
    }

    public Long getId() {
        return id;
    }

    public AlbumViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AlbumViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public ArtistNameEnum getArtist() {
        return artist;
    }

    public AlbumViewModel setArtist(ArtistNameEnum artist) {
        this.artist = artist;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public AlbumViewModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
