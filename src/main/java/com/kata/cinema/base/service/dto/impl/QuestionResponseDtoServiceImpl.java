package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.QuestionResponseDtoDao;
import com.kata.cinema.base.dto.response.QuestionResponseDto;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.service.dto.QuestionResponseDtoService;
import com.kata.cinema.base.service.entity.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionResponseDtoServiceImpl extends PaginationDtoServiceImpl<QuestionResponseDto> implements QuestionResponseDtoService {

    private final NewsService newsService;

    @Autowired
    public QuestionResponseDtoServiceImpl(QuestionResponseDtoDao questionResponseDtoDao, NewsService newsService) {
        super(questionResponseDtoDao);
        this.newsService = newsService;
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
}
