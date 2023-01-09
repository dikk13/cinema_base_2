package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionMovieStudioDao;
import com.kata.cinema.base.dao.util.JpaResultHelper;
import com.kata.cinema.base.models.ProductionStudioMovie;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class ProductionMovieStudioDaoImpl extends AbstractDaoImpl<Long, ProductionStudioMovie>
        implements ProductionMovieStudioDao {

    @Override
    public Optional<ProductionStudioMovie> getStudioByMovieId(Long movieId) {
        return  jpaResultHelper(entityManager.createQuery("select psm from ProductionStudioMovie psm " +
                        "where psm.movie.id = :id", ProductionStudioMovie.class)
                .setParameter("id", movieId));
    }

    @Override
    public Optional<ProductionStudioMovie> getByMovieIdStudioId(Long movieId, Long studioId) {
        TypedQuery<ProductionStudioMovie> typedQuery = entityManager.createQuery(
                "select psm from ProductionStudioMovie psm where psm.movie.id = :movieId and psm.studio.id = :studioId",
                ProductionStudioMovie.class)
                .setParameter("movieId", movieId)
                .setParameter("studioId", studioId);
        return JpaResultHelper.jpaResultHelper(typedQuery);
    }
}
