package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.AvailableOnlineMovie;

import java.util.List;

public interface AvailableOnlineMovieDAO extends AbstractDao<Long, AvailableOnlineMovie> {
    List<AvailableOnlineMovie> getAvailableMovie();
}
