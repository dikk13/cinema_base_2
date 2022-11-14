package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.ReviewMovieResponseDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.ReviewMovieResponseDto;
import com.kata.cinema.base.service.dto.ReviewMovieResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewMovieResponseDtoServiceImpl extends PaginationDtoServiceImpl<ReviewMovieResponseDto> implements ReviewMovieResponseDtoService {

    private final ReviewMovieResponseDtoDao responseDtoDao;
    private final ReviewResponseDtoServiceImpl reviewResponseDtoService;

    @Autowired
    public ReviewMovieResponseDtoServiceImpl(ReviewMovieResponseDtoDao responseDtoDao, ReviewResponseDtoServiceImpl reviewResponseDtoService) {
        super(responseDtoDao);
        this.responseDtoDao = responseDtoDao;
        this.reviewResponseDtoService = reviewResponseDtoService;
    }

    @Override
    public PageDto<ReviewMovieResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {

        PageDto<ReviewMovieResponseDto> pageDto = new PageDto<>();
        List<ReviewMovieResponseDto> reviewMovieResponseDtoList = responseDtoDao.getItemsDto(currentPage, itemsOnPage, parameters);

        if (reviewMovieResponseDtoList.size() != 0) {

            for (ReviewMovieResponseDto reviewMovieResponseDto : reviewMovieResponseDtoList) {
                reviewMovieResponseDto.setPage(reviewResponseDtoService.getPageDtoWithParameters(currentPage, itemsOnPage, parameters));
            }

            pageDto.setCount(responseDtoDao.getResultTotal(parameters));
            pageDto.setEntities(reviewMovieResponseDtoList);

            return pageDto;

        }

        pageDto.setCount(0L);
        pageDto.setEntities(null);
        return pageDto;

    }
}
