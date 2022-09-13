package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;

import java.util.List;


public interface GenreDao extends AbstractDao<Long, Genre>{
    List<GenreResponseDto> getAllGenreResponseDto ();
}
