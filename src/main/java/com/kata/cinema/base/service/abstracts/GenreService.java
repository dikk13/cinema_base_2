package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;

import java.util.List;
import java.util.Optional;


public interface GenreService  extends AbstractService<Long, Genre>  {
    List <GenreResponseDto> getAllGenreResponseDto ();

}
