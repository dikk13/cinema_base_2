package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.HistoryMovieDao;
import com.kata.cinema.base.models.HistoryMovie;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryMovieDaoImpl extends AbstractDaoImpl<Long, HistoryMovie> implements HistoryMovieDao {
}
