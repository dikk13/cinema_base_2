package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.ProductionStudio;

import java.util.List;
import java.util.Optional;

public interface AvailableOnlineMovieService extends AbstractService<Long, AvailableOnlineMovie>{

    Optional<AvailableOnlineMovie> getAvailableOnlineMovieById(Long movieId);


}

