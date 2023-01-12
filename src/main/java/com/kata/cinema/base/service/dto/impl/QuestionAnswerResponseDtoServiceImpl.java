package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.QuestionAnswerResponseDtoDao;
import com.kata.cinema.base.dto.response.AnswerResponseDto;
import com.kata.cinema.base.dto.response.QuestionAnswerResponseDto;
import com.kata.cinema.base.service.dto.QuestionAnswerResponseDtoService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionAnswerResponseDtoServiceImpl implements QuestionAnswerResponseDtoService {
    private final QuestionAnswerResponseDtoDao questionAnswerResponseDtoDao;

    public QuestionAnswerResponseDtoServiceImpl(QuestionAnswerResponseDtoDao questionAnswerResponseDtoDao) {
        this.questionAnswerResponseDtoDao = questionAnswerResponseDtoDao;
    }

    @Override
    public QuestionAnswerResponseDto getQuestionAnswerResponseDtoById(Long id) {
        return questionAnswerResponseDtoDao.getQuestionAnswerResponseDtoById(id);
    }

    @Override
    public List<AnswerResponseDto> getAnswerResponseDtoListById(Long id) {
        return questionAnswerResponseDtoDao.getAnswerResponseDtoListById(id);
    }

    @Override
    public Boolean isRightAnswer(Long questionId, Long answerId) {
        return questionAnswerResponseDtoDao.isRightAnswer(questionId ,answerId);
    }
}
