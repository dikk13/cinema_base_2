package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.MoviePersonResponseDtoDao;
import com.kata.cinema.base.dto.response.MoviePersonResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviePersonResponseDtoDaoImpl implements MoviePersonResponseDtoDao {

    //TODO метод не реализован, удалить если не используется
    @Override
    public List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id) {
        return null;
    }
}
