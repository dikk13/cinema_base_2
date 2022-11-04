package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AvailableOnlineMovieDAO;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import org.springframework.stereotype.Repository;

@Repository
public class AvailableOnlineMovieDAOImpl extends AbstractDaoImpl<Long, AvailableOnlineMovie> implements AvailableOnlineMovieDAO {
}