package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Comments;

import java.util.List;

public interface CommentsDao extends AbstractDao<Long, Comments> {
    List<Comments> getCommentsListByNewsId(long id);


}
