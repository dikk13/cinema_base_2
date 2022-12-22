package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.ReviewResponseDtoDao;
import com.kata.cinema.base.dto.response.ReviewResponseDto;
import com.kata.cinema.base.service.dto.ReviewResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewResponseDtoServiceImpl extends PaginationDtoServiceImpl<ReviewResponseDto> implements ReviewResponseDtoService {

    private final ReviewResponseDtoDao responseDtoDao;

    @Autowired
    public ReviewResponseDtoServiceImpl(ReviewResponseDtoDao responseDtoDao) {
        super(responseDtoDao);
        this.responseDtoDao = responseDtoDao;
    }

    @Override
    public List<ReviewResponseDto> getAllUnmoderatedReviewsList() {
        return responseDtoDao.getAllUnmoderatedReviewsList();
    }
}
