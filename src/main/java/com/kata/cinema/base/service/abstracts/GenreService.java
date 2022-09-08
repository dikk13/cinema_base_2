package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;

import java.util.List;
import java.util.Optional;


public interface GenreService  extends AbstractService<Long, Genre>  {
    List<GenreResponseDto> getAllGenreResponseDto ();
    @Override
    List<Genre> getAll();

    @Override
    void create(Genre entity);

    @Override
    void update(Genre entity);

    @Override
    void delete(Genre entity);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Genre> getById(Long id);

    @Override
    boolean existById(Long id);
}
