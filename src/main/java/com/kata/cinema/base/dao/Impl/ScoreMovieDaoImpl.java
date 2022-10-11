package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreMovieDao;
import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ScoreMovieDaoImpl implements ScoreMovieDao {

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return null;
    }
}
