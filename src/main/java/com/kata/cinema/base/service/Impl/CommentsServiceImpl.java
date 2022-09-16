package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.CommentsDao;
import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.service.abstracts.CommentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsDao commentsDao;

    public CommentsServiceImpl(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }
    @Override
    public List<Comments> getAllCommentsByNewsId(long id) {
        return commentsDao.getCommentsListByNewsId(id);
    }
}
