package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.converter.GenreMapper;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;
import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminGenreRestController {
    private final GenreService  genreService;


    @Autowired
    public AdminGenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping("/api/moderator/genres")
    public ResponseEntity<List<GenreResponseDto>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAll().stream().
                map(GenreResponseDto::new).
                collect(Collectors.toList()));
    }

    @DeleteMapping("/api/moderator/genres/{id}")
    public ResponseEntity<List<GenreResponseDto>> deleteGenreById(@PathVariable("id") long id) {
        genreService.deleteById(id);
        return ResponseEntity.ok(genreService.getAll().stream().
                map(GenreResponseDto::new).
                collect(Collectors.toList()));
    }

    @PutMapping("/api/moderator/genres/{id}")
    public void updateGenre(@RequestBody Genre genre,@PathVariable ("id") long id ) {
        if(genreService.existById(id)){
            genreService.update(genre);
        }
        else {throw new RuntimeException(){};
        }
    }

    @PostMapping("/api/moderator/genres")
    public void addNewGenre(@RequestBody GenreResponseDto genreDto) {
        genreService.create(GenreMapper.CONVERT.toGenre(genreDto));
    }
}