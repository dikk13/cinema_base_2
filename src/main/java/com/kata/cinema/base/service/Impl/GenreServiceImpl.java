package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;


    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }



    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void create(Genre genre) {
        genreDao.create(genre);

    }

    @Override
    public void update(Genre genre) {
        genreDao.update(genre);

    }

    @Override
    public void delete(Genre genre) {
        genreDao.update(genre);

    }

    @Override
    public void deleteById(Long id) {
        genreDao.deleteById(id);

    }

    @Override
    public Optional<Genre> getById(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public boolean existById(Long id) {
        return genreDao.existById(id);
    }
}
