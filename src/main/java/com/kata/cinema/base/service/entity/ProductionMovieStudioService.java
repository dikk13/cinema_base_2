package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.ProductionStudioMovie;

import java.util.Optional;

public interface ProductionMovieStudioService extends AbstractService<Long, ProductionStudioMovie> {
    Optional<ProductionStudioMovie> getStudioByMovieId(Long id);
}
