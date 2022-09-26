package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.ProductionMovieStudioDao;
import com.kata.cinema.base.models.ProductionStudioMovie;
import com.kata.cinema.base.service.abstracts.ProductionMovieStudioService;
import org.springframework.stereotype.Service;

@Service
public class ProductionMovieStudioServiceImpl extends AbstractServiceImpl<Long, ProductionStudioMovie>
        implements ProductionMovieStudioService {

    private final ProductionMovieStudioDao productionMovieStudioDao;

    public ProductionMovieStudioServiceImpl(AbstractDao<Long, ProductionStudioMovie> abstractDao,
                                            ProductionMovieStudioDao productionMovieStudioDao) {
        super(abstractDao);
        this.productionMovieStudioDao = productionMovieStudioDao;
    }

    @Override
    public ProductionStudioMovie getStudioByMovieId(Long id) {
        return productionMovieStudioDao.getStudioByMovieId(id);
    }
}
