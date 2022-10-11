package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import com.kata.cinema.base.mappers.ScoreMovieMapper;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.abstracts.MovieService;
import com.kata.cinema.base.service.abstracts.ScoreMovieResponseDtoService;
import com.kata.cinema.base.service.abstracts.ScoreMovieService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserScoreMovieRestController {

    private final ScoreMovieResponseDtoService scoreMovieResponseDtoService;
    private final ScoreMovieService scoreMovieService;
    private final ScoreMovieMapper scoreMovieMapper;
    private final MovieService movieService;

    public UserScoreMovieRestController(ScoreMovieResponseDtoService scoreMovieResponseDtoService, ScoreMovieService scoreMovieService, ScoreMovieMapper scoreMovieMapper, MovieService movieService) {
        this.scoreMovieResponseDtoService = scoreMovieResponseDtoService;
        this.scoreMovieService = scoreMovieService;
        this.scoreMovieMapper = scoreMovieMapper;
        this.movieService = movieService;
    }

    @PostMapping("/api/user/movies/{id}/score")
    public void addScoreMovie(@PathVariable("id") Long movieId,
                              @RequestBody ScoreMovieResponseDto scoreMovieResponseDto,
                              @RequestParam("score") Integer score) {
        Score newScore = scoreMovieMapper.toScore(scoreMovieResponseDto);
        Optional<Movie> movie = movieService.getById(movieId);
        newScore.setScore(score);
        newScore.setMovie(movie.orElse(null));
        scoreMovieResponseDtoService.create(newScore);

    }

    @PatchMapping("/api/user/movies/{id}/score")
    public void updateScoreMovie(@PathVariable("id") Long movieId,
                                 @RequestBody ScoreMovieResponseDto scoreMovieResponseDto,
                                 @RequestParam("score") Integer score) {

        Score updatedScore = scoreMovieMapper.toScore(scoreMovieResponseDto);
        Optional<Movie> movie = movieService.getById(movieId);
        updatedScore.setScore(score);
        updatedScore.setMovie(movie.orElse(null));
        scoreMovieResponseDtoService.update(updatedScore);

    }

    @DeleteMapping("/api/user/scores/{id}")
    public void deleteScoreMovie(@PathVariable("id") Long id) {
        scoreMovieResponseDtoService.deleteById(id);
    }

    @GetMapping("/api/user/scores/page/{pageNumber}")
    public PageDto<ScoreMovieResponseDto> getScoreMovie(@PathVariable("pageNumber") Integer pageNumber,
                                                        @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
                                                        @RequestParam(value = "sortScoreType", required = false, defaultValue = "DATE_ASC") SortScoreType sortScoreType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sortScoreType", sortScoreType);
        PageDto<ScoreMovieResponseDto> scoreMovieResponseDtoPage = scoreMovieService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
        return scoreMovieResponseDtoPage;
    }
}
