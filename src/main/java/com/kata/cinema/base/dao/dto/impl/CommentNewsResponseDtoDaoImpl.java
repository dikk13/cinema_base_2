package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CommentNewsResponseDtoDao;
import com.kata.cinema.base.dto.response.CommentNewsResponseDto;
import com.kata.cinema.base.models.enums.TypeRating;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommentNewsResponseDtoDaoImpl implements CommentNewsResponseDtoDao {

    private final EntityManager entityManager;

    public CommentNewsResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CommentNewsResponseDto> getCommentNewsResponseDtoListByNewsId(Long newsId) {
        return entityManager
                .createQuery("SELECT NEW com.kata.cinema.base.dto.response.CommentNewsResponseDto(" +
                                "c.id, c.message, c.parentId, c.level, c.date, c.user, " +
                                "((SELECT COUNT (rc) FROM  RatingComment rc " +
                                "WHERE rc.comments.id = c.id and rc.rating = :like))" +
                                " - " +
                                "((SELECT COUNT (rc) FROM  RatingComment rc " +
                                "WHERE rc.comments.id = c.id and rc.rating = :dislike)))" +
                                "FROM Comment c " +
                                "WHERE c.news.id = :newsId"
                        , CommentNewsResponseDto.class)
                .setParameter("newsId", newsId)
                .setParameter("like", TypeRating.LIKE)
                .setParameter("dislike", TypeRating.DISLIKE)
                .getResultList();
    }
}
