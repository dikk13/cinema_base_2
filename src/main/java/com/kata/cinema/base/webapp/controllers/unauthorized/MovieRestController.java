package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.dto.response.ReviewResponseDto;
import com.kata.cinema.base.mappers.ExcertionMapper;
import com.kata.cinema.base.models.Excertion;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.dto.ExcertionResponseDtoService;
import com.kata.cinema.base.service.dto.MovieViewResponseDtoService;
import com.kata.cinema.base.service.dto.ReviewResponseDtoService;
import com.kata.cinema.base.service.entity.ExcertionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
public class MovieRestController {

    private final ReviewResponseDtoService responseDtoService;
    private final MovieViewResponseDtoService movieViewResponseDtoService;
    private final ExcertionResponseDtoService excertionResponseDtoService;
    private final ExcertionService excertionService;
    private final ExcertionMapper excertionMapper;

    @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
    public PageDto<ReviewResponseDto> getReview(
            @PathVariable("id") Long movieId,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "typeReview", required = false) TypeReview typeReview,
            @RequestParam(value = "reviewSortType", required = false, defaultValue = "DATE_ASC") ReviewSortType reviewSortType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        parameters.put("typeReview", typeReview);
        parameters.put("reviewSortType", reviewSortType);
        return responseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }


    @GetMapping("/api/movies/{id}")
    public ResponseEntity<MovieViewResponseDto> getMovie(@PathVariable("id") Long movieId) {
        return new ResponseEntity<>(movieViewResponseDtoService.getMovieViewResponseDtoByMovieId(movieId), HttpStatus.OK);
    }

    @PostMapping("/api/movies/{id}/excertions")
    public ResponseEntity<HttpStatus> createMovieExcertion(@PathVariable("id") Long movieId,
                                                           @RequestBody ExcertionRequestDto excertionRequestDto) {
        Movie movie = excertionResponseDtoService.findMovieById(movieId);
        Excertion newExcertion = excertionMapper.toExcertion(excertionRequestDto);
        newExcertion.setMovie(movie);
        excertionService.create(newExcertion);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/api/movies/{id}/excertions/page/{pageNumber}")
    public PageDto<ExcertionResponseDto> getMovieExcertion(@PathVariable("id") long movieId,
                                                           @PathVariable("pageNumber") Integer pageNumber,
                                                           @RequestParam(value = "itemsOnPage", required = true, defaultValue = "10") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        return excertionResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }
}
