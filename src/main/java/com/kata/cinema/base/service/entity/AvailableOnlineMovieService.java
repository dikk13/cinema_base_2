package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.AvailableOnlineMovie;

import java.util.List;

public interface AvailableOnlineMovieService extends AbstractService<Long, AvailableOnlineMovie>{
    List<AvailableOnlineMovie> getAvailableMovie();
}

