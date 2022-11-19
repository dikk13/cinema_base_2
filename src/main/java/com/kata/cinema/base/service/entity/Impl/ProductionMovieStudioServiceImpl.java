package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionMovieStudioDao;
import com.kata.cinema.base.models.ProductionStudioMovie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ProductionMovieStudioService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductionMovieStudioServiceImpl extends AbstractServiceImpl<Long, ProductionStudioMovie>
        implements ProductionMovieStudioService {

    private final ProductionMovieStudioDao productionMovieStudioDao;

    public ProductionMovieStudioServiceImpl(ProductionMovieStudioDao productionMovieStudioDao) {
        super(productionMovieStudioDao);
        this.productionMovieStudioDao = productionMovieStudioDao;
    }

    @Override
    public Optional<ProductionStudioMovie> getStudioByMovieId(Long id) {
        return productionMovieStudioDao.getStudioByMovieId(id);
    }
}
