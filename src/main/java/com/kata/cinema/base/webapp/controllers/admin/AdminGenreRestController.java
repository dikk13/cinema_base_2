package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.mappers.GenreMapper;

import com.kata.cinema.base.exception.GenreIdNotFoundException;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.dto.GenreResponseDto;
import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AdminGenreRestController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;


    @Autowired
    public AdminGenreRestController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }


    @GetMapping("/api/moderator/genres")
    public ResponseEntity<List<GenreResponseDto>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAllGenreResponseDto());
    }

    @DeleteMapping("/api/moderator/genres/{id}")
    public void deleteGenreById(@PathVariable("id") long id) {
        genreService.deleteById(id);

    }

    @PutMapping("/api/moderator/genres/{id}")
    public void updateGenre(@RequestBody Genre genre, @PathVariable("id") long id) {
        if (genreService.existById(id)) {
            genreService.update(genre);
        } else {
            throw new GenreIdNotFoundException("Genre with this ID: " + id + " ,don't found ") {};
        }
    }

    @PostMapping("/api/moderator/genres")
    public void addNewGenre(@RequestBody GenreResponseDto genreDto) {
        genreService.create(genreMapper.toGenre(genreDto));
    }

}