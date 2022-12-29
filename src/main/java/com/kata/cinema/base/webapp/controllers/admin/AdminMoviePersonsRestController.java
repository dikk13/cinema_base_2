package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.CastRequestDto;
import com.kata.cinema.base.exception.MoviePersonNotFoundException;
import com.kata.cinema.base.mappers.MoviePersonMapper;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.service.entity.MoviePersonService;
import com.kata.cinema.base.service.entity.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/movies/{id}/persons")
@RequiredArgsConstructor
public class AdminMoviePersonsRestController {

    private final MovieService movieService;
    private final MoviePersonMapper moviePersonMapper;
    private final MoviePersonService moviePersonService;

    @PostMapping
    public ResponseEntity<Void> addPersonToMovie(@PathVariable("id") Long movieId,
                                                 @RequestBody List<CastRequestDto> personList) {
        for (CastRequestDto castRequestDto:personList) {
            MoviePerson moviePerson = moviePersonMapper.toMoviePerson(castRequestDto);
            moviePerson.setMovie(movieService.getMovieById(movieId));
            moviePersonService.create(moviePerson);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{personId}?professionId={Long}")
    public ResponseEntity<Void> updateMoviePersons(@PathVariable("id") Long movieId,
                                                   @PathVariable Long personId,
                                                   @PathVariable long Long,
                                                   @RequestBody CastRequestDto castRequestDto) {
        Optional<MoviePerson> moviePerson = moviePersonService.getMoviePersonByMovieIdPersonIdProfessionId(movieId, personId, Long);
        if (moviePerson.isPresent()) {
            moviePersonService.updateById(moviePerson.get().getId(), moviePersonMapper.toMoviePerson(castRequestDto));
        } else {
            MoviePerson newMoviePerson = moviePersonMapper.toMoviePerson(castRequestDto);
            newMoviePerson.setMovie(movieService.getMovieById(movieId));
            moviePersonService.create(newMoviePerson);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{personId}?professionId={Long}")
    public ResponseEntity<Void> deleteMoviePersons(@PathVariable("id") Long movieId,
                                                   @PathVariable Long personId,
                                                   @PathVariable long Long) {
        Optional<MoviePerson> moviePerson = moviePersonService.getMoviePersonByMovieIdPersonIdProfessionId(movieId, personId, Long);
        if (moviePerson.isPresent()) {
            moviePersonService.deleteById(moviePerson.get().getId());
        } else {
            throw new MoviePersonNotFoundException("Персона в указанном фильме не найдена");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
