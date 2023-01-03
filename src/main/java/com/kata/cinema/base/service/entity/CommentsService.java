package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Comments;

import java.util.List;

public interface CommentsService extends AbstractService<Long, Comments> {

    List<Comments> getAllCommentsByNewsId(long id);

    List<Comments> getAllCommentsNoModerate(long pageId);
}
