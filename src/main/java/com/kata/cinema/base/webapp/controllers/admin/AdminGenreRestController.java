package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.models.dto.GenreResponseDto;
import com.kata.cinema.base.service.abstracts.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminGenreRestController {
    private final AbstractService<Long, GenreResponseDto> abstractService;


    @Autowired
    public AdminGenreRestController(AbstractService<Long, GenreResponseDto> abstractService) {
        this.abstractService = abstractService;
    }


    @GetMapping("/api/moderator/genres")
    public ResponseEntity<List<GenreResponseDto>> getAllGenre() {
        List<GenreResponseDto> genres = abstractService.getAll();
        return ResponseEntity.ok(genres);
    }

    @DeleteMapping("/api/moderator/genres/{id}")
    public ResponseEntity<List<GenreResponseDto>> deleteGenre(@PathVariable("id") long id) {
        abstractService.deleteById(id);
        return ResponseEntity.ok(abstractService.getAll());
    }

    @PutMapping("/api/moderator/genres/{id}?name=")
    public ResponseEntity<List<GenreResponseDto>> updateGenre(@RequestBody GenreResponseDto genreDto) {
        abstractService.update(genreDto);
        return ResponseEntity.ok(abstractService.getAll());
    }

    @PostMapping("/api/moderator/genres?name=")
    public ResponseEntity<List<GenreResponseDto>> addNewGenre(@RequestBody GenreResponseDto genreDto) {
        abstractService.create(genreDto);
        return ResponseEntity.ok(abstractService.getAll());
    }
}