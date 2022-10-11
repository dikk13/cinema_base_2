package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreDao;
import com.kata.cinema.base.models.Score;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDaoImpl extends AbstractDaoImpl<Long, Score> implements ScoreDao {
    @Override
    public Score getScoreByUserAndMovieId(Long userId, Long movieId) {
        return entityManager.
                createQuery("select s from Score s where s.movie.id =: movieId and s.user.id =: userId", Score.class)
                .setParameter("movieId", movieId)
                .setParameter("userId", userId)
                .getSingleResult();
        }
}
