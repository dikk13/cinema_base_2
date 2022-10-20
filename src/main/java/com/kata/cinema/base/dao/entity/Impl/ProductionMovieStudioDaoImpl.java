package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionMovieStudioDao;
import com.kata.cinema.base.models.ProductionStudioMovie;
import org.springframework.stereotype.Repository;

@Repository
public class ProductionMovieStudioDaoImpl extends AbstractDaoImpl<Long, ProductionStudioMovie>
        implements ProductionMovieStudioDao {

    @Override
    public ProductionStudioMovie getStudioByMovieId(Long movieId) {
        return  entityManager.createQuery("select psm from ProductionStudioMovie psm " +
                        "where psm.movie.id = :id", ProductionStudioMovie.class)
                .setParameter("id", movieId)
                .getSingleResult();
    }
}
