package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Comment;

import java.util.List;

public interface CommentsDao extends AbstractDao<Long, Comment> {
    List<Comment> getCommentsListByNewsId(long id);
}
