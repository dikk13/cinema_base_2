package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.QuestionAnswer;
import com.kata.cinema.base.models.Result;

import java.util.List;

public interface ResultDao extends AbstractDao<Long, Result>{

    Result getResultByQuestionAnswerList(List<QuestionAnswer> fromDTO);

}
