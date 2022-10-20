package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.ProductionStudioMovie;

public interface ProductionMovieStudioDao extends AbstractDao<Long, ProductionStudioMovie>{
    ProductionStudioMovie getStudioByMovieId(Long id);
}
