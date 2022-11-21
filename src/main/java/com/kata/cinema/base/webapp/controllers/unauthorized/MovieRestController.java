package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.dto.response.ReviewMovieResponseDto;
import com.kata.cinema.base.mappers.ExcertionMapper;
import com.kata.cinema.base.models.Excertion;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.dto.ExcertionResponseDtoService;
import com.kata.cinema.base.service.dto.MovieViewResponseDtoService;
import com.kata.cinema.base.service.dto.ReviewMovieResponseDtoService;
import com.kata.cinema.base.service.entity.ExcertionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
public class MovieRestController {

    private final ReviewMovieResponseDtoService reviewMovieResponseDtoService;
    private final MovieViewResponseDtoService movieViewResponseDtoService;
    private final ExcertionResponseDtoService excertionResponseDtoService;

    @GetMapping("/{id}/reviews/page/{pageNumber}")
    public PageDto<ReviewMovieResponseDto> getReview(
            @PathVariable("id") Long movieId,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "typeReview", required = false) TypeReview typeReview,
            @RequestParam(value = "reviewSortType", required = false, defaultValue = "DATE_ASC") ReviewSortType reviewSortType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        parameters.put("typeReview", typeReview);
        parameters.put("reviewSortType", reviewSortType);
        return reviewMovieResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieViewResponseDto> getMovie(@PathVariable("id") Long movieId) {
        return new ResponseEntity<>(movieViewResponseDtoService.getMovieViewResponseDtoByMovieId(movieId), HttpStatus.OK);
    }

    @GetMapping("/{id}/excertions/page/{pageNumber}")
    public ResponseEntity<PageDto<ExcertionResponseDto>> getMovieExcertion(@PathVariable("id") long movieId,
                                                           @PathVariable("pageNumber") Integer pageNumber,
                                                           @RequestParam(value = "itemsOnPage", required = true, defaultValue = "10") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        return ResponseEntity.ok(excertionResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters));
    }
}
