package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.response.SearchResponseDto;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchHeaderDtoController {

    private final CollectionService collectionService;
    private final MovieService movieService;
    private final PersonService personService;

    @Autowired
    public SearchHeaderDtoController(CollectionService collectionService, MovieService movieService, PersonService personService) {
        this.collectionService = collectionService;
        this.movieService = movieService;
        this.personService = personService;
    }


    @GetMapping("/api/search")
    public ResponseEntity<SearchResponseDto> getAllMoviesPersonsCollection(@RequestParam(value = "filterPattern") String filter) {

        return new ResponseEntity<>(new SearchResponseDto(movieService.titleMovie(filter),
                collectionService.titleCollection(filter),
                personService.namePerson(filter)), HttpStatus.OK);
    }
}