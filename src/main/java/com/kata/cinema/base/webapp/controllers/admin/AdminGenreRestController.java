package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.mappers.GenreMapper;

import com.kata.cinema.base.exception.GenreIdNotFoundException;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.dto.response.GenreResponseDto;
import com.kata.cinema.base.service.entity.GenreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class AdminGenreRestController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;


    @Autowired
    public AdminGenreRestController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }


    @GetMapping("/api/admin/genres")
    public ResponseEntity<List<GenreResponseDto>> getAllGenre() {
        return ResponseEntity.ok(genreMapper.toDTOList(genreService.getAll()));
    }

    @DeleteMapping("/api/admin/genres/{id}")
    public void deleteGenreById(@PathVariable("id") long id) {
        genreService.deleteById(id);

    }

    @PutMapping("/api/admin/genres/{id}")
    public void updateGenre(@RequestParam (name = "name") String genreName, @PathVariable("id") long id) {
        Optional<Genre> genreContainer= genreService.getById(id);
        if (genreContainer.isPresent()) {
            genreContainer.get().setName(genreName);
            genreService.update(genreContainer.get());
        }
        else {
            throw new GenreIdNotFoundException("Genre with this ID: " + id + " ,don't found ") {};
        }
    }

    @PostMapping("/api/admin/genres")
    public void addNewGenre(@RequestBody GenreResponseDto genreDto) {
        genreService.create(genreMapper.toGenre(genreDto));
    }

}