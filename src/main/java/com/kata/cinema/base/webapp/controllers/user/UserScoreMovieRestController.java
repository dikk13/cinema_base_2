package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import com.kata.cinema.base.mappers.ScoreMovieMapper;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.abstracts.ScoreMovieResponseDtoService;
import com.kata.cinema.base.service.abstracts.ScoreMovieService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserScoreMovieRestController {

    private final ScoreMovieResponseDtoService scoreMovieResponseDtoService;
    private final ScoreMovieService scoreMovieService;
    private final ScoreMovieMapper scoreMovieMapper;


    public UserScoreMovieRestController(ScoreMovieResponseDtoService scoreMovieResponseDtoService, ScoreMovieService scoreMovieService, ScoreMovieMapper scoreMovieMapper) {
        this.scoreMovieResponseDtoService = scoreMovieResponseDtoService;
        this.scoreMovieService = scoreMovieService;
        this.scoreMovieMapper = scoreMovieMapper;

    }

    @PostMapping("/movies/{id}/score")
    public void addScoreMovie(@PathVariable("id") Long movieId,
                              @RequestBody ScoreMovieResponseDto scoreMovieResponseDto,
                              @RequestParam("score") Integer score) {

        Score newScore = scoreMovieMapper.toScore(scoreMovieResponseDto);
        Movie movie = scoreMovieResponseDtoService.findMovie(movieId);
        newScore.setScore(score);
        newScore.setMovie(movie);
        scoreMovieResponseDtoService.create(newScore);
    }

    @PatchMapping("/movies/{id}/score")
    public void updateScoreMovie(@PathVariable("id") Long movieId,
                                 @RequestBody ScoreMovieResponseDto scoreMovieResponseDto,
                                 @RequestParam("score") Integer score) {

        Score updatedScore = scoreMovieMapper.toScore(scoreMovieResponseDto);
        Movie movie = scoreMovieResponseDtoService.findMovie(movieId);
        updatedScore.setScore(score);
        updatedScore.setMovie(movie);
        scoreMovieResponseDtoService.update(updatedScore);

    }

    @DeleteMapping("/scores/{id}")
    public void deleteScoreMovie(@PathVariable("id") Long id) {
        scoreMovieResponseDtoService.deleteById(id);
    }

    @GetMapping("/scores/page/{pageNumber}")
    public PageDto<ScoreMovieResponseDto> getScoreMovie(@PathVariable("pageNumber") Integer pageNumber,
                                                        @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
                                                        @RequestParam(value = "sortScoreType", required = false, defaultValue = "DATE_ASC") SortScoreType sortScoreType) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("sortScoreType", sortScoreType);
        return scoreMovieService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }
}
