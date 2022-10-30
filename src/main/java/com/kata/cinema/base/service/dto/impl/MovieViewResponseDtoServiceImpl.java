package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.MovieViewResponseDtoDao;
import com.kata.cinema.base.dto.response.CastResponseDto;
import com.kata.cinema.base.dto.response.MoviePersonResponseDto;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.exception.MovieIdNotFoundException;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.dto.CastResponseDtoService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.dto.MoviePersonResponseDtoService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.dto.MovieViewResponseDtoService;
import com.kata.cinema.base.service.entity.ScoreService;
import com.kata.cinema.base.service.entity.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieViewResponseDtoServiceImpl implements MovieViewResponseDtoService {

    private final GenreService genreService;
    private final UserService userService;
    private final MoviePersonResponseDtoService moviePersonResponseDtoService;
    private final CastResponseDtoService castResponseDtoService;
    private final MovieService movieService;
    private final ScoreService scoreService;
    private final MovieViewResponseDtoDao movieViewResponseDtoDao;

    public MovieViewResponseDtoServiceImpl(GenreService genreService, UserService userService,
                                           MoviePersonResponseDtoService moviePersonResponseDtoService,
                                           CastResponseDtoService castResponseDtoService, MovieService movieService, ScoreService scoreService, MovieViewResponseDtoDao movieViewResponseDtoDao) {
        this.genreService = genreService;
        this.userService = userService;
        this.moviePersonResponseDtoService = moviePersonResponseDtoService;
        this.castResponseDtoService = castResponseDtoService;
        this.movieService = movieService;
        this.scoreService = scoreService;
        this.movieViewResponseDtoDao = movieViewResponseDtoDao;
    }

    @Override
    public MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long movieId) {

        if (!movieService.existById(movieId)) throw new MovieIdNotFoundException("Фильм не найден");
        List<MoviePersonResponseDto> persons = moviePersonResponseDtoService.getMoviePersonResponseDtoListByMovieId(movieId);
        List<CastResponseDto> casts = castResponseDtoService.getCastResponseDtoWithMoviePersonDtosListByMovieId(movieId, persons);
        String genres = genreService.getGenresOfMovieByMovieId(movieId);
        Integer myScore = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String username = auth.getName();
            try {
                User user = userService.getByEmail(username).get();
                myScore = scoreService.getScoreByMovieIdAndUserId(movieId, user.getId());
            } catch (UsernameNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }

        MovieViewResponseDto movieViewResponseDto = movieViewResponseDtoDao.getMovieViewResponseDtoByMovieId(movieId);
        movieViewResponseDto.setGenres(genres);
        movieViewResponseDto.setMyScore(myScore);
        movieViewResponseDto.setCasts(casts);
        return movieViewResponseDto;
    }
}
