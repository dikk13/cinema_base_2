package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.ReviewMovieResponseDtoDao;
import com.kata.cinema.base.dto.response.ReviewMovieResponseDto;
import com.kata.cinema.base.service.dto.ReviewMovieResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMovieResponseDtoServiceImpl extends PaginationDtoServiceImpl<ReviewMovieResponseDto> implements ReviewMovieResponseDtoService {

    private final ReviewMovieResponseDtoDao responseDtoDao;

    @Autowired
    public ReviewMovieResponseDtoServiceImpl(ReviewMovieResponseDtoDao responseDtoDao) {
        super(responseDtoDao);
        this.responseDtoDao = responseDtoDao;
    }
}
