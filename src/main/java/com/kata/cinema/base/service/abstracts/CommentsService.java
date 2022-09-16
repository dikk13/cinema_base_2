package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Comments;

import java.util.List;

public interface CommentsService {

    List<Comments> getAllCommentsByNewsId(long id);
}
