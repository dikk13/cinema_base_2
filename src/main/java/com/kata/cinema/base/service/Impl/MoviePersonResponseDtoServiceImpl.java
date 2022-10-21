package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.MoviePersonResponseDtoDao;
import com.kata.cinema.base.dto.MoviePersonResponseDto;
import com.kata.cinema.base.service.abstracts.MoviePersonResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviePersonResponseDtoServiceImpl implements MoviePersonResponseDtoService {

    private final MoviePersonResponseDtoDao moviePersonResponseDtoDao;

    public MoviePersonResponseDtoServiceImpl(MoviePersonResponseDtoDao moviePersonResponseDtoDao) {
        this.moviePersonResponseDtoDao = moviePersonResponseDtoDao;
    }

    @Override
    public List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id) {
        return moviePersonResponseDtoDao.getMoviePersonResponseDtoListByMovieId(id);
    }
}
