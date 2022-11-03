package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CommentNewsResponseDtoDao;
import com.kata.cinema.base.dto.response.CommentNewsResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CommentNewsResponseDtoDaoImpl implements CommentNewsResponseDtoDao {

    private final EntityManager entityManager;

    public CommentNewsResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CommentNewsResponseDto> getCommentNewsResponseDtoListByNewsId(Long newsId) {
        return entityManager.createQuery("SELECT NEW com.kata.cinema.base.dto.response.CommentNewsResponseDto(" +
                                "c.id, c.message, c.parentId, c.level, c.date, rc.rating, c.user) " +
                                "FROM Comment c LEFT JOIN RatingComment rc ON c.id = rc.comment.id " +
                                "WHERE c.news.id = :newsId"
                        , CommentNewsResponseDto.class)
                .setParameter("newsId", newsId)
                .getResultList();
    }
}
