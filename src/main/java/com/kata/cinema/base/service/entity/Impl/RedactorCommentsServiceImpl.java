package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.RedactorCommentsDao;
import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.RedactorCommentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedactorCommentsServiceImpl extends AbstractServiceImpl<Long, RedactorComment> implements RedactorCommentsService {

    private final RedactorCommentsDao redactorCommentsDao;

    public RedactorCommentsServiceImpl(RedactorCommentsDao redactorCommentsDao) {
        super(redactorCommentsDao);
        this.redactorCommentsDao = redactorCommentsDao;
    }

    @Override
    @Transactional
    public void create(RedactorComment redactorComment) {
        redactorCommentsDao.create(redactorComment);
    }

}
