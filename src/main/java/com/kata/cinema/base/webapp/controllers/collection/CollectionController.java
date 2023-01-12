package com.kata.cinema.base.webapp.controllers.collection;

import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/collections")

public class CollectionController {

    private final CollectionService collectionService;
    private final MovieService movieService;

    public CollectionController(CollectionService collectionService, MovieService movieService) {
        this.collectionService = collectionService;
        this.movieService = movieService;
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable("id")Long id) {
        Collection collection = collectionService.getById(id).orElseThrow();
        collection.setEnable(false);
        collectionService.update(collection);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable("id")Long id) {
        Collection collection = collectionService.getById(id).orElseThrow();
        collection.setEnable(true);
        collectionService.update(collection);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/movies")
    public ResponseEntity<Void> addMovies(@PathVariable("id")Long id, List<Movie> movieIds) {
        Collection collection = collectionService.getById(id).orElseThrow();
        collection.setMovies(movieIds);
        collectionService.update(collection);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}/movies/{mId}")
    public void deleteMovies(@PathVariable("id") Long id, @PathVariable("mId")Long movieId) {
        Collection collection = collectionService.getById(id).orElseThrow();
        List<Movie> list = collection.getMovies();
        list.remove(movieService.getById(movieId).orElseThrow());
        collection.setMovies(list);
        collectionService.update(collection);
    }
}
