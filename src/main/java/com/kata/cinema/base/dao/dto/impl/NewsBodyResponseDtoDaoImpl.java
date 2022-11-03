package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.NewsBodyResponseDtoDao;
import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class NewsBodyResponseDtoDaoImpl implements NewsBodyResponseDtoDao {

    private final EntityManager entityManager;

    public NewsBodyResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public NewsBodyResponseDto getNewsBodyResponseDtoByNewsId(Long newsId) {
        return entityManager.createQuery(
                        "SELECT NEW com.kata.cinema.base.dto.response.NewsBodyResponseDto (n.id, n.rubric, " +
                                "n.date, n.title, n.htmlBody, " +
                                "(SELECT COUNT (c) FROM Comment c WHERE c.news.id = :newsId), " +
                                "CONCAT (n.user.first_name, ' ', n.user.last_name))" +
                                "FROM News n WHERE n.id = :newsId", NewsBodyResponseDto.class)
                .setParameter("newsId", newsId).getSingleResult();
    }

}
