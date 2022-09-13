package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.ReviewResponseDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ReviewResponseDto;
import com.kata.cinema.base.service.abstracts.ReviewResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ReviewResponseDtoServiceImpl implements ReviewResponseDtoService {

    private final ReviewResponseDtoDao responseDtoDao;

    @Autowired
    public ReviewResponseDtoServiceImpl(ReviewResponseDtoDao responseDtoDao) {
        this.responseDtoDao = responseDtoDao;
    }


    @Override
    public PageDto<ReviewResponseDto> getReviewResponseDto() {
//        return responseDtoDao.getReviewResponseDto();
        return null;
    }
}
