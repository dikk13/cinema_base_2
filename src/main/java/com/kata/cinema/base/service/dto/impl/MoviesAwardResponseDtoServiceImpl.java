package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.MoviesAwardResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardResponseDto;
import com.kata.cinema.base.service.dto.MoviesAwardResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesAwardResponseDtoServiceImpl implements MoviesAwardResponseDtoService {

    MoviesAwardResponseDtoDao moviesAwardResponseDtoDao;

    public MoviesAwardResponseDtoServiceImpl(MoviesAwardResponseDtoDao moviesAwardResponseDtoDao) {
        this.moviesAwardResponseDtoDao = moviesAwardResponseDtoDao;
    }

    @Override
    public List<AwardResponseDto> getMoviesAwards(Long id) {
        return moviesAwardResponseDtoDao.getMoviesAwards(id);
    }

}
