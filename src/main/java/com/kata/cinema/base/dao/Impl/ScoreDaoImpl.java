package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreDao;
import com.kata.cinema.base.models.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreDaoImpl extends AbstractDaoImpl<Long, Score> implements ScoreDao {
    @Override
    public List<Score> getScoreListByMovieId(Long movieId) {
        return entityManager.createQuery("select s from Score s where s.movie.id =: id")
                .setParameter("id", movieId)
                .getResultList();
        }
}
