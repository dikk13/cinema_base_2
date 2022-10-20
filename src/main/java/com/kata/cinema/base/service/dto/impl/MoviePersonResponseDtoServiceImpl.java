package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.MoviePersonResponseDtoDao;
import com.kata.cinema.base.dto.response.MoviePersonResponseDto;
import com.kata.cinema.base.service.dto.MoviePersonResponseDtoService;
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
