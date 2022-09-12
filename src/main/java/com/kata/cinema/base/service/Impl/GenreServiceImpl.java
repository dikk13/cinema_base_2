package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;
import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GenreServiceImpl extends AbstractServiceImpl<Long, Genre> implements GenreService {
    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(AbstractDao<Long, Genre> abstractDao, GenreDao genreDao) {
        super(abstractDao);
        this.genreDao = genreDao;
    }

    @Override
    public List<GenreResponseDto> getAllGenreResponseDto() {
        return genreDao.getAllGenreResponseDto();
    }

}
