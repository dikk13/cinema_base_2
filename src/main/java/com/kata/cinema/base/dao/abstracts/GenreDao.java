package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.GenreResponseDto;
import com.kata.cinema.base.models.Genre;

import java.util.List;
import java.util.Map;


public interface GenreDao extends AbstractDao<Long, Genre>{
    List<GenreResponseDto> getAllGenreResponseDto ();

    Map<Long, List<String>> getGenresMap(String moviesId);

}
