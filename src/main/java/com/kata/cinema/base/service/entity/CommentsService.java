package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Comment;

import java.util.List;

public interface CommentsService extends AbstractService<Long, Comment> {

    List<Comment> getAllCommentsByNewsId(long id);
}
