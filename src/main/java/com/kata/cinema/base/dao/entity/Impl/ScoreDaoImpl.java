package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ScoreDao;
import com.kata.cinema.base.models.Score;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDaoImpl extends AbstractDaoImpl<Long, Score> implements ScoreDao {

    @Override
    public Integer getScoreByMovieIdAndUserId(Long movieId, Long userId) {
        return (Integer) entityManager.createQuery("select s.score from Score s where s.movie.id =: movieId " +
                        "and s.user.id =: userId")
                .setParameter("movieId", movieId)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
