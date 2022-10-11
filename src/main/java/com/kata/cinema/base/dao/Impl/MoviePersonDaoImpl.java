package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviePersonDaoImpl extends AbstractDaoImpl<Long, MoviePerson> implements MoviePersonDao {
    @Override
    public List<MoviePerson> getMoviePersonListByMovieId(Long movieId) {
        return entityManager.createQuery("select mp from MoviePerson mp where mp.movie.id =: movieId")
                .setParameter("movieId", movieId)
                .getResultList();
    }
}
