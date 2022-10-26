package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.*;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.dto.MovieViewResponseDtoService;
import com.kata.cinema.base.service.dto.ReviewResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class MovieRestController {

    private final ReviewResponseDtoService responseDtoService;
    private final MovieViewResponseDtoService movieViewResponseDtoService;

    @Autowired
    public MovieRestController(ReviewResponseDtoService responseDtoService,
                               MovieViewResponseDtoService movieViewResponseDtoService) {
        this.responseDtoService = responseDtoService;
        this.movieViewResponseDtoService = movieViewResponseDtoService;
    }

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
}
