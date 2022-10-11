package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.service.abstracts.MoviePersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviePersonServiceImpl extends AbstractServiceImpl<Long, MoviePerson> implements MoviePersonService {

    private final MoviePersonDao moviePersonDao;

    protected MoviePersonServiceImpl(AbstractDao<Long, MoviePerson> abstractDao, MoviePersonDao moviePersonDao) {
        super(abstractDao);
        this.moviePersonDao = moviePersonDao;
    }

    @Override
    public List<MoviePerson> getMoviePersonListByMovieId(Long movieId) {
        return moviePersonDao.getMoviePersonListByMovieId(movieId);
    }
}
