package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreMovieDao;
import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import com.kata.cinema.base.models.enums.SortScoreType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ScoreMovieDaoImpl implements ScoreMovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.ScoreMovieResponseDto (s.id, s.score, s.date) " +
                        "from score s join movie m on m.id =: s.movie_id join user u on u.id =: s.user_id where u.id =: userId"
                        + getSort((SortScoreType) parameters.get("sortScoreType")), ScoreMovieResponseDto.class)
                .setParameter("userId", parameters.get("userId"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return entityManager.createQuery("select count (s) from Score s join user u on u.id = s.user_id where u.id =: userId", Long.class)
                .setParameter("userId", parameters.get("userId"))
                .getSingleResult();
    }

    private String getSort(SortScoreType sortScoreType) {
        StringBuilder sort = new StringBuilder();
        switch (sortScoreType) {
            case DATE_ASC -> sort.append(" order by s.date");

            case SCORE_ASC -> sort.append(" order by s.score");

            case NAME_ASC -> sort.append(" order by m.name");

            case COUNT_SCORE_ASC -> sort.append(" order by count (s)");

            case DATE_RELEASE_ASC -> sort.append(" order by m.dateRelease");
        }
        return sort.toString();
    }

}
