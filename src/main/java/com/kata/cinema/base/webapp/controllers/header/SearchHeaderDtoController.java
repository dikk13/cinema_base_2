package com.kata.cinema.base.webapp.controllers.header;

import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.dto.search.SearchResponseDto;
import com.kata.cinema.base.service.abstracts.CollectionService;
import com.kata.cinema.base.service.abstracts.MovieService;
import com.kata.cinema.base.service.abstracts.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search?filterPattern=")
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


    @GetMapping
    public ResponseEntity<SearchResponseDto> getAllMoviesPersonsCollection(@RequestParam ResponseEntity<SearchResponseDto> allParams) {
            ResponseEntity.ok(collectionService.titleCollection());
            ResponseEntity.ok(movieService.titleMovie());
            ResponseEntity.ok(personService.namePerson());
            return allParams;
    }
}
// GET /api/search?filterPattern= возвращает SearchResponseDto
//filterPattern - может принимать значения как название фильма, имя персоны, название коллекции