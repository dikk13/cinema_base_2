package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.models.Comments;

import java.util.List;

public interface CommentsDao extends AbstractDao<Long, Comments> {
    List<Comments> getCommentsListByNewsId(long id);

    void create(CommentsRequestDto entity, long userId, long newsId);
}
