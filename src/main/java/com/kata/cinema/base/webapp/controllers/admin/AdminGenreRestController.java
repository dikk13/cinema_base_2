package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;
import com.kata.cinema.base.service.abstracts.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminGenreRestController {
    private final AbstractService<Long, Genre> abstractService;


    @Autowired
    public AdminGenreRestController(AbstractService<Long, Genre> abstractService) {
        this.abstractService = abstractService;
    }
    
    @GetMapping("/api/moderator/genres")
    public ResponseEntity<List<GenreResponseDto>> getAllGenre() {
        return ResponseEntity.ok( abstractService.getAll().stream().
                map(GenreResponseDto :: new).
                collect(Collectors.toList()));
    }

    @DeleteMapping("/api/moderator/genres/{id}")
    public ResponseEntity<List<GenreResponseDto>> deleteGenre(@PathVariable long id) {
        abstractService.deleteById(id);
        return ResponseEntity.ok(abstractService.getAll().stream().
                map(GenreResponseDto :: new).
                collect(Collectors.toList()));
    }
    @PutMapping("/api/moderator/genres/{id}?name=")
    public ResponseEntity<List<GenreResponseDto>> updateGenre(@RequestBody GenreResponseDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre.setId(genreDto.getId());
        abstractService.update(genre);
        return ResponseEntity.ok(abstractService.getAll().stream().
                map(GenreResponseDto :: new).
                collect(Collectors.toList()));
    }
    @PostMapping("/api/moderator/genres?name=")
    public ResponseEntity<List<GenreResponseDto>> addNewGenre(@RequestBody GenreResponseDto genreDto) {
        Genre genre = new Genre();
        genre.setId(genreDto.getId());
        genre.setName(genreDto.getName());
        abstractService.create(genre);
        return ResponseEntity.ok(abstractService.getAll().stream().
                map(GenreResponseDto :: new).
                collect(Collectors.toList()));
    }
}