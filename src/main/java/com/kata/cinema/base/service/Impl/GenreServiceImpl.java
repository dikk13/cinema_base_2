package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;
import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }


    @Override
    @Transactional
    public List<GenreResponseDto> getAllGenreResponseDto() {
        return genreDao.getAllGenreResponseDto();
    }

    @Override
    @Transactional
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    @Transactional
    public void create(Genre genre) {
        genreDao.create(genre);

    }

    @Override
    @Transactional
    public void update(Genre genre) {
        genreDao.update(genre);

    }

    @Override
    @Transactional
    public void delete(Genre genre) {
        genreDao.delete(genre);

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        genreDao.deleteById(id);

    }

    @Override
    @Transactional
    public Optional<Genre> getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    @Transactional
    public boolean existById(Long id) {
        return genreDao.existById(id);
    }
}
