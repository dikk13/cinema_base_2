package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.ProductionStudioMovie;

public interface ProductionMovieStudioService extends AbstractService<Long, ProductionStudioMovie> {
    ProductionStudioMovie getStudioByMovieId(Long id);
}
