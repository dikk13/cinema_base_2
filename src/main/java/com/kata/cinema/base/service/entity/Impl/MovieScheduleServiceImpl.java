package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieScheduleDao;
import com.kata.cinema.base.models.MovieSchedule;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MovieScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieScheduleServiceImpl extends AbstractServiceImpl<Long, MovieSchedule> implements MovieScheduleService {

    private final MovieScheduleDao movieScheduleDao;

    @Autowired
    public MovieScheduleServiceImpl(MovieScheduleDao movieScheduleDao){
        super(movieScheduleDao);
        this.movieScheduleDao = movieScheduleDao;
    }
}
