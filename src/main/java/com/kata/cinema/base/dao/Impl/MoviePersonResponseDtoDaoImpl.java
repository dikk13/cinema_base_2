package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.MoviePersonResponseDtoDao;
import com.kata.cinema.base.dto.MoviePersonResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviePersonResponseDtoDaoImpl implements MoviePersonResponseDtoDao {

    //TODO метод не реализован
    @Override
    public List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id) {
        return null;
    }
}
