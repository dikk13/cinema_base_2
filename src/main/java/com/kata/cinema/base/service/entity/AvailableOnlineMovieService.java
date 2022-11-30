package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.AvailableOnlineMovie;

import java.util.List;
import java.util.Optional;

public interface AvailableOnlineMovieService extends AbstractService<Long, AvailableOnlineMovie>{

    Optional<AvailableOnlineMovie> getAvailableOnlineMovieById(Long movieId);


}

