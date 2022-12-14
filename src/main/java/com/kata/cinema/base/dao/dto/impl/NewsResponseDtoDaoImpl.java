package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.NewsResponseDtoDao;
import com.kata.cinema.base.dto.response.NewsResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class NewsResponseDtoDaoImpl implements NewsResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.NewsResponseDto" +
                "(" +
                "n.id, n.rubric, n.date, n.title, n.previewUrl, count (c)" +
                ") " +
                "from News n left join Comment c on c.news.id = n.id where (n.rubric = :rubric or :rubric is null) group by n.id", NewsResponseDto.class)
                .setParameter("rubric", parameters.get("rubric"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return entityManager.createQuery("select count (n) from News n where (n.rubric = :rubric or :rubric is null)", Long.class)
                .setParameter("rubric", parameters.get("rubric"))
                .getSingleResult();
    }

    @Override
    public List<NewsResponseDto> getNewsResponseDtoByMovieId(Integer count, Long movieId) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.NewsResponseDto" +
                "(" +
                "n.id, n.rubric, n.date, n.title, n.previewUrl, count (c)" +
                ") " +
                "from News n left join Comment c on c.news.id = n.id join n.movies m where m.id = :movieId group by n.id", NewsResponseDto.class)
                .setParameter("movieId", movieId)
                .setMaxResults(count)
                .getResultList();
    }

}
