package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.ProductionStudioMovie;

import java.util.Optional;

public interface ProductionMovieStudioDao extends AbstractDao<Long, ProductionStudioMovie>{
    Optional<ProductionStudioMovie> getStudioByMovieId(Long id);
}
