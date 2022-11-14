package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.AnswerDao;
import com.kata.cinema.base.models.Answer;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AnswerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AnswerServiceImpl extends AbstractServiceImpl<Long, Answer> implements AnswerService {

    private final AnswerDao answerDao;

    public AnswerServiceImpl(AbstractDao<Long, Answer> abstractDao, AnswerDao answerDao) {
        super(abstractDao);
         this.answerDao = answerDao;
    }
}
