package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Comments;

import java.util.List;

public interface CommentsService extends AbstractService<Long, Comments> {

    List<Comments> getAllCommentsByNewsId(long id);
    void create(Comments comments);
    void create(Comments comments, long userId, long newsId);
}
