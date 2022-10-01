package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.ProductionStudioMovie;

public interface ProductionMovieStudioDao extends AbstractDao<Long, ProductionStudioMovie>{
    ProductionStudioMovie getStudioByMovieId(Long id);
}
