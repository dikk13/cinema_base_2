package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.*;
import com.kata.cinema.base.models.HistoryMovie;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.HistoryType;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.dto.*;
import com.kata.cinema.base.service.entity.HistoryService;
import com.kata.cinema.base.service.entity.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/movies")
public class MovieRestController {

    private final ReviewMovieResponseDtoService reviewMovieResponseDtoService;
    private final MovieViewResponseDtoService movieViewResponseDtoService;
    private final ExcertionResponseDtoService excertionResponseDtoService;
    private final MovieService movieService;
    private final HistoryService historyService;
    private final NewsResponseDtoService newsResponseDtoService;
    private final MoviesAwardResponseDtoService moviesAwardResponseDtoService;

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
    public ResponseEntity<MovieViewResponseDto> getMovie(@PathVariable("id") Long movieId, @AuthenticationPrincipal User currentUser) {
        MovieViewResponseDto movieViewResponseDto = movieViewResponseDtoService.getMovieViewResponseDtoByMovieId(movieId);

        if (currentUser != null) {
            Optional<Movie> movieHistory = movieService.getById(movieId);
            if (movieHistory.isPresent()) {
                HistoryMovie historyMovie = new HistoryMovie();
                historyMovie.setMovie(movieHistory.get());
                historyMovie.setDate(LocalDateTime.now());
                historyMovie.setUser(currentUser);
                historyMovie.setType(HistoryType.MOVIE);
                historyService.addToHistoryMovie(historyMovie);
            }
        }

        return new ResponseEntity<>(movieViewResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/excertions/page/{pageNumber}")
    public ResponseEntity<PageDto<ExcertionResponseDto>> getMovieExcertion(@PathVariable("id") long movieId,
                                                           @PathVariable("pageNumber") Integer pageNumber,
                                                           @RequestParam(value = "itemsOnPage", required = true, defaultValue = "10") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        return ResponseEntity.ok(excertionResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters));
    }

    @GetMapping("/{id}/materials")
    public List<NewsResponseDto> getNewsMovies(@PathVariable("id") long movieId,
                                               @RequestParam(value = "count") Integer count) {
        return newsResponseDtoService.getNewsResponseDtoByMovieId(count, movieId);
    }

    @GetMapping("/{id}/awards")
    public List<AwardResponseDto> getMoviesAwards(@PathVariable Long id) {
        return moviesAwardResponseDtoService.getMoviesAwards(id);
    }

}
