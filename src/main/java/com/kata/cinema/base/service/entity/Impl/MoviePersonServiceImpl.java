package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MoviePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoviePersonServiceImpl extends AbstractServiceImpl<Long, MoviePerson> implements MoviePersonService {

    private final MoviePersonDao moviePersonDao;

    @Autowired
    protected MoviePersonServiceImpl(MoviePersonDao moviePersonDao) {
        super(moviePersonDao);
        this.moviePersonDao = moviePersonDao;
    }

    public boolean isProfessionIsBeingUsed(Profession profession) {
        return moviePersonDao.isProfessionIsBeingUsed(profession);
    }
}
