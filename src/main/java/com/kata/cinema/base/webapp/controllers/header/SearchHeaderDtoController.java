package com.kata.cinema.base.webapp.controllers.header;

import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.dto.search.SearchResponseDto;
import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Person;
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
// GET /api/search?filterPattern= возвращает SearchResponseDto
//filterPattern - может принимать значения как название фильма, имя персоны, название коллекции