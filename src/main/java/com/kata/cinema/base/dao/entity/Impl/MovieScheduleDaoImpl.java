package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieScheduleDao;
import com.kata.cinema.base.models.MovieSchedule;
import org.springframework.stereotype.Repository;

@Repository
public class MovieScheduleDaoImpl extends AbstractDaoImpl<Long, MovieSchedule> implements MovieScheduleDao {
}
