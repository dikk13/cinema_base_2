package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.ResultDao;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Result;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ResultServiceImpl extends AbstractServiceImpl<Long, Result> implements ResultService {

    private final ResultDao resultDao;

    public ResultServiceImpl(AbstractDao<Long, Result> abstractDao, ResultDao resultDao) {
        super(abstractDao);
        this.resultDao = resultDao;
    }

    @Override
    public List<Result> getResultByQuestionAnswerList(List<QuestionAnswerRequestDto> questionAnswerRequestDtoList) {

        return resultDao.getResultByQuestionAnswerList(questionAnswerRequestDtoList);

    }

}
