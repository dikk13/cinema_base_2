package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.mappers.GenreMapper;

import com.kata.cinema.base.exception.GenreIdNotFoundException;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.dto.response.GenreResponseDto;
import com.kata.cinema.base.service.entity.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/admin/genres")
@AllArgsConstructor
public class AdminGenreRestController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAllGenre() {
        return ResponseEntity.ok(genreMapper.toDTOList(genreService.getAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable("id") long id) {
        genreService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGenre(@RequestParam (name = "name") String genreName, @PathVariable("id") long id) {
        Optional<Genre> genreContainer= genreService.getById(id);
        if (genreContainer.isPresent()) {
            genreContainer.get().setName(genreName);
            genreService.update(genreContainer.get());
        }
        else {
            throw new GenreIdNotFoundException("Genre with this ID: " + id + " ,don't found ") {};
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<Void> addNewGenre(@RequestBody GenreResponseDto genreDto) {
        genreService.create(genreMapper.toGenre(genreDto));
        return ResponseEntity.ok(null);
    }
}