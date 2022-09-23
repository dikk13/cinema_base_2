package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.CommentsDao;
import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.service.abstracts.CommentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentsServiceImpl extends AbstractServiceImpl<Long, Comments> implements CommentsService {

    private final CommentsDao commentsDao;

    public CommentsServiceImpl(AbstractDao<Long, Comments> abstractDao, CommentsDao commentsDao) {
        super(abstractDao);
        this.commentsDao = commentsDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comments> getAllCommentsByNewsId(long id) {
        return commentsDao.getCommentsListByNewsId(id);
    }

    @Override
    @Transactional
    public void create(Comments comments) {
        commentsDao.create(comments);
    }
}
