package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.QuestionResponseDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.QuestionResponseDto;
import com.kata.cinema.base.service.dto.QuestionNewsResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class QuestionNewsResponseDtoServiceImpl extends PaginationDtoServiceImpl<QuestionResponseDto> implements QuestionNewsResponseDtoService {

    private final QuestionResponseDtoDao questionResponseDtoDao;

    @Autowired
    public QuestionNewsResponseDtoServiceImpl(QuestionResponseDtoDao questionResponseDtoDao) {
        super(questionResponseDtoDao);
        this.questionResponseDtoDao = questionResponseDtoDao;
    }

    @Override
    public List<QuestionResponseDto> getQuestionResponseDtoByNewsId(Integer itemsOnPage, Long newsId) {
        return questionResponseDtoDao.getQuestionResponseDtoByNewsId(itemsOnPage, newsId);
    }

    @Override
    public PageDto<QuestionResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return super.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
    }
}
