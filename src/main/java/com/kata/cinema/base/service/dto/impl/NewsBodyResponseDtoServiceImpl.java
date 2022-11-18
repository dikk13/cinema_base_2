package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.NewsBodyResponseDtoDao;
import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
import com.kata.cinema.base.exception.NewsIdNotFoundException;
import com.kata.cinema.base.service.dto.NewsBodyResponseDtoService;
import com.kata.cinema.base.service.entity.NewsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsBodyResponseDtoServiceImpl implements NewsBodyResponseDtoService {

    private final NewsBodyResponseDtoDao newsBodyResponseDtoDao;
    private final NewsService newsService;

    public NewsBodyResponseDtoServiceImpl(NewsBodyResponseDtoDao newsBodyResponseDtoDao,
                                          NewsService newsService) {
        this.newsBodyResponseDtoDao = newsBodyResponseDtoDao;
        this.newsService = newsService;
    }

    @Override
    public Optional<NewsBodyResponseDto> getNewsBodyResponseDtoById(Long id) {
        if (newsService.getNewsById(id).getUser() == null) {
            throw new NewsIdNotFoundException("Запись не найдена");
        }
        return newsBodyResponseDtoDao.getNewsBodyResponseDtoByNewsId(id);
    }

}
