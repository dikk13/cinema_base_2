package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.SearchMovieRequestDto;
import com.kata.cinema.base.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.dto.response.SearchResponseDto;
import com.kata.cinema.base.service.dto.SearchMovieResponseDtoService;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class SearchHeaderDtoController {

    private final CollectionService collectionService;
    private final MovieService movieService;
    private final PersonService personService;
    private final SearchMovieResponseDtoService searchMovieResponseDtoService;


    @Autowired
    public SearchHeaderDtoController(CollectionService collectionService, MovieService movieService, PersonService personService, SearchMovieResponseDtoService searchMovieResponseDtoService) {
        this.collectionService = collectionService;
        this.movieService = movieService;
        this.personService = personService;
        this.searchMovieResponseDtoService = searchMovieResponseDtoService;
    }


    @GetMapping("/api/search")
    public ResponseEntity<SearchResponseDto> getAllMoviesPersonsCollection(@RequestParam(value = "filterPattern") String filter) {
        return new ResponseEntity<>(new SearchResponseDto(movieService.titleMovie(filter),
                collectionService.titleCollection(filter),
                personService.namePerson(filter)), HttpStatus.OK);
    }

    //TODO @RequestParam (Map<Long, List<Long>>)
    @PostMapping("api/search/movies/{id}/authors/page/{pageNumber}")
    public ResponseEntity<PageDto<SearchMovieResponseDto>> getAuthors(@PathVariable("id") long movieId,
                                                                      @PathVariable("pageNumber") Integer pageNumber,
                                                                      @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage,
                                                                      @RequestBody SearchMovieRequestDto searchMovieRequestDto) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("professionId", searchMovieRequestDto.getProfessionId());
        parameters.put("personsId", searchMovieRequestDto.getPersonsId());
        parameters.put("movieId", movieId);
        return ResponseEntity.ok(searchMovieResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters));
    }

}