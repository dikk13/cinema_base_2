package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.mappers.ExcertionMapper;
import com.kata.cinema.base.models.Excertion;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.dto.ExcertionResponseDtoService;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import com.kata.cinema.base.service.entity.ExcertionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/excertions")
public class AdminExcertionRestController {

    private final ExcertionService excertionService;
    private final ExcertionMapper excertionMapper;
    private final ExcertionResponseDtoService excertionResponseDtoService;

    public AdminExcertionRestController(ExcertionService excertionService, ExcertionMapper excertionMapper, ExcertionResponseDtoService excertionResponseDtoService) {
        this.excertionService = excertionService;
        this.excertionMapper = excertionMapper;
        this.excertionResponseDtoService = excertionResponseDtoService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcertion(@PathVariable("id") long id) {
        excertionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExcertion(@PathVariable("id") long id,
                                                @RequestBody ExcertionRequestDto excertionRequestDto) {
            excertionService.updateById(id, excertionMapper.toExcertion(excertionRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/persons/{id}")
    public ResponseEntity<HttpStatus> createPersonExcertion(@PathVariable("id") long personId,
                                                            @RequestBody ExcertionRequestDto excertionRequestDto) {
        Person person = excertionResponseDtoService.findPersonById(personId);
        Excertion newExcertion = excertionMapper.toExcertion(excertionRequestDto);
        newExcertion.setPerson(person);
        excertionService.create(newExcertion);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/movies/{id}")
    public ResponseEntity<HttpStatus> createMovieExcertion(@PathVariable("id") Long movieId,
                                                           @RequestBody ExcertionRequestDto excertionRequestDto) {
        Movie movie = excertionResponseDtoService.findMovieById(movieId);
        Excertion newExcertion = excertionMapper.toExcertion(excertionRequestDto);
        newExcertion.setMovie(movie);
        excertionService.create(newExcertion);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
