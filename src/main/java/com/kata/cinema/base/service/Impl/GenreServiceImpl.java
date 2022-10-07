package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.service.abstracts.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class GenreServiceImpl extends AbstractServiceImpl<Long, Genre> implements GenreService {

    public GenreServiceImpl(AbstractDao<Long, Genre> abstractDao) {
        super(abstractDao);
    }
}
