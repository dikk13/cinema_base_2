package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.mappers.ScoreMovieMapper;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.SortScoreType;
import com.kata.cinema.base.service.dto.ScoreMovieResponseDtoService;
import com.kata.cinema.base.service.entity.ScoreMovieService;
import com.kata.cinema.base.service.entity.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserScoreMovieRestController {

    private final ScoreMovieResponseDtoService scoreMovieResponseDtoService;
    private final ScoreMovieService scoreMovieService;
    private final ScoreMovieMapper scoreMovieMapper;
    private final ScoreService scoreService;


    @PostMapping("/movies/{id}/score")
    public ResponseEntity<Void> addScoreMovie(@PathVariable("id") Long movieId,
                                              @RequestBody ScoreMovieResponseDto scoreMovieResponseDto,
                                              @RequestParam("score") Integer score) {

        Score newScore = scoreMovieMapper.toScore(scoreMovieResponseDto);
        Movie movie = scoreMovieResponseDtoService.findMovie(movieId);
        newScore.setScore(score);
        newScore.setMovie(movie);
        scoreService.create(newScore);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/movies/{id}/score")
    public ResponseEntity<Void> updateScoreMovie(@PathVariable("id") Long movieId,
                                                 @RequestBody ScoreMovieResponseDto scoreMovieResponseDto,
                                                 @RequestParam("score") Integer score) {

        Score updatedScore = scoreMovieMapper.toScore(scoreMovieResponseDto);
        Movie movie = scoreMovieResponseDtoService.findMovie(movieId);
        updatedScore.setScore(score);
        updatedScore.setMovie(movie);
        scoreService.update(updatedScore);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Void> deleteScoreMovie(@PathVariable("id") Long id) {
        //TODO Добавить проверку на удаление
        scoreService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/scores/page/{pageNumber}")
    public ResponseEntity<PageDto<ScoreMovieResponseDto>> getScoreMovie(@PathVariable("pageNumber") Integer pageNumber,
                                                                        @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
                                                                        @RequestParam(value = "sortScoreType", required = false, defaultValue = "DATE_ASC") SortScoreType sortScoreType) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", userId);
        parameters.put("sortScoreType", sortScoreType);
        return ResponseEntity.ok(scoreMovieService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters));
    }
}
