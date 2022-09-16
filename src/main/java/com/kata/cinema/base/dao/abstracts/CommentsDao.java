package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Comments;

import java.util.List;

public interface CommentsDao extends AbstractDao {
    List<Comments> getCommentsListByNewsId(long id);
}
