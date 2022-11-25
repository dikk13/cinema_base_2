package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AvailableOnlineMovieDAO;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.models.FolderMovie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AvailableOnlineMovieDAOImpl extends AbstractDaoImpl<Long, AvailableOnlineMovie> implements AvailableOnlineMovieDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<AvailableOnlineMovie> getAvailableOnlineMovieById(Long movieId) {
        try{
            return Optional.of(entityManager.createQuery(
                    "SELECT aom FROM AvailableOnlineMovie aom "+ "WHERE  aom.movie.id=: movieId",
                AvailableOnlineMovie.class)
                    .setParameter("movieId",movieId)
                    .getSingleResult());
                } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
