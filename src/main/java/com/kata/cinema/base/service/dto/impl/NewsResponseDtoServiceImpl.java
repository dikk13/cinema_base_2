package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.NewsResponseDtoDao;
import com.kata.cinema.base.dto.response.NewsResponseDto;
import com.kata.cinema.base.service.dto.NewsResponseDtoService;
import org.springframework.stereotype.Service;

@Service
public class NewsResponseDtoServiceImpl extends PaginationDtoServiceImpl<NewsResponseDto> implements NewsResponseDtoService {

    private final NewsResponseDtoDao newsResponseDtoDao;

    public NewsResponseDtoServiceImpl(NewsResponseDtoDao newsResponseDtoDao) {
        super(newsResponseDtoDao);
        this.newsResponseDtoDao = newsResponseDtoDao;
    }
}
