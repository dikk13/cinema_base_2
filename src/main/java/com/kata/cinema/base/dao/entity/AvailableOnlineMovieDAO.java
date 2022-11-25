package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.AvailableOnlineMovie;

import java.util.Optional;

public interface AvailableOnlineMovieDAO extends AbstractDao<Long, AvailableOnlineMovie> {

    Optional<AvailableOnlineMovie> getAvailableOnlineMovieById(Long movieId);
}
