package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.NewsResponseDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.NewsResponseDto;
import com.kata.cinema.base.service.dto.NewsResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsResponseDtoServiceImpl extends PaginationDtoServiceImpl<NewsResponseDto> implements NewsResponseDtoService {

    private final NewsResponseDtoDao newsResponseDtoDao;

    public NewsResponseDtoServiceImpl(NewsResponseDtoDao newsResponseDtoDao) {
        super(newsResponseDtoDao);
        this.newsResponseDtoDao = newsResponseDtoDao;
    }

    @Override
    public PageDto<NewsResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return super.getPageDtoWithParameters(currentPage, itemsOnPage, parameters);
    }


    @Override
    public List<NewsResponseDto> getNewsResponseDtoByMovieId(Integer itemsOnPage, Long movieId) {
        return newsResponseDtoDao.getNewsResponseDtoByMovieId(itemsOnPage, movieId);
    }

}
