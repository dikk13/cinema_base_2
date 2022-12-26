package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.QuestionResponseDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.QuestionResponseDto;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.service.dto.QuestionNewsResponseDtoService;
import com.kata.cinema.base.service.entity.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionNewsResponseDtoServiceImpl extends PaginationDtoServiceImpl<QuestionResponseDto> implements QuestionNewsResponseDtoService {

    private final QuestionResponseDtoDao questionResponseDtoDao;

    private final NewsService newsService;

    @Autowired
    public QuestionNewsResponseDtoServiceImpl(QuestionResponseDtoDao questionResponseDtoDao, NewsService newsService) {
        super(questionResponseDtoDao);
        this.questionResponseDtoDao = questionResponseDtoDao;
        this.newsService = newsService;
    }

    @Override
    public List<QuestionResponseDto> getQuestionResponseDtoByNewsId(Integer itemsOnPage, Long newsId) {
        return questionResponseDtoDao.getQuestionResponseDtoByNewsId(itemsOnPage, newsId);
    }

    @Override
    public News findNews(Long newsId) {
        Optional<News> news = newsService.getById(newsId);
        if (news.isPresent()) {
            return news.get();
        } else {
            throw new NullPointerException("Новости не найдены");
        }
    }

    @Override
    public PageDto<QuestionResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return super.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
    }


}
