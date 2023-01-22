package com.kata.cinema.base.webapp.controllers.critic;

import com.kata.cinema.base.dto.request.CriticReviewRequestDto;
import com.kata.cinema.base.dto.response.CriticReviewResponseDto;
import com.kata.cinema.base.mappers.CriticalReviewMapper;
import com.kata.cinema.base.models.CriticalReview;
import com.kata.cinema.base.service.dto.CriticResponseDtoService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/critic")
public class CriticController {

    private final CriticResponseDtoService criticReviewResponseDtoService;
    private final CriticalReviewMapper mapper;
    private final MovieService movieService;

    public CriticController(CriticResponseDtoService criticReviewResponseDtoService, CriticalReviewMapper mapper, MovieService movieService) {
        this.criticReviewResponseDtoService = criticReviewResponseDtoService;
        this.mapper = mapper;
        this.movieService = movieService;
    }

    @PostMapping("/reviews/movies/{id}")
    private void reviewById(@PathVariable("id") Long id, CriticReviewRequestDto criticReviewRequestDto) {
        CriticalReview review = mapper.toCriticalReview(criticReviewRequestDto);
        review.setMovie(movieService.getMovieById(id));
    }

    @GetMapping("/reviews")
    private List<CriticReviewResponseDto> listReviews () {
        return criticReviewResponseDtoService.getAllReviews();
    }

    @DeleteMapping("/reviews/{id}")
    private void deleteReview(@PathVariable("id") Long id) {
        criticReviewResponseDtoService.deleteById(id);
    }

    @PutMapping("/reviews/{id}")
    private void addReview(@PathVariable("id") Long id, @RequestBody CriticReviewRequestDto criticReviewRequestDto) {
        criticReviewResponseDtoService.updateById(id, criticReviewRequestDto);
    }
}
