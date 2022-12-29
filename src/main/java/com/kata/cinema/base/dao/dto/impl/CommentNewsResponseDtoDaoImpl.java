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
        List<CommentNewsResponseDto> commentNewsResponseDtoList = entityManager
                .createQuery("SELECT NEW com.kata.cinema.base.dto.response.CommentNewsResponseDto(" +
                                "c.id, c.message, c.parentId, c.level, c.date, c.user) " +
                                "FROM Comment c " +
                                "WHERE c.news.id = :newsId"
                        , CommentNewsResponseDto.class)
                .setParameter("newsId", newsId)
                .getResultList();
        commentNewsResponseDtoList.forEach(c -> c.setRating(getCommentRating(c.getId())));
        return commentNewsResponseDtoList;

    }

    public Integer getCommentRating(Long commentId) {
        Integer likes =(Integer) entityManager.createQuery(
                "SELECT COUNT (rc) FROM  RatingComment rc WHERE rc.comment.id = :commentId and rc.rating = :like")
                .setParameter("commentId", commentId)
                .setParameter("like", TypeRating.LIKE)
                .getSingleResult();
        Integer dislikes =(Integer) entityManager.createQuery(
                        "SELECT COUNT (rc) FROM  RatingComment rc WHERE rc.comment.id = :commentId and rc.rating = :like")
                .setParameter("commentId", commentId)
                .setParameter("like", TypeRating.DISLIKE)
                .getSingleResult();
        return likes - dislikes;
    }
}
