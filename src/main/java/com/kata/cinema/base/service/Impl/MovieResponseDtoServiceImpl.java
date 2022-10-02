package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.service.abstracts.MovieResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieResponseDtoServiceImpl extends PaginationDtoServiceImpl <MovieResponseDto> implements MovieResponseDtoService {

    private final MovieResponseDtoDao movieResponseDtoDao;
    public MovieResponseDtoServiceImpl(MovieResponseDtoDao movieResponseDtoDao) {
        super(movieResponseDtoDao);
        this.movieResponseDtoDao = movieResponseDtoDao;
    }

    @Override
    public PageDto<MovieResponseDto> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        PageDto <MovieResponseDto> pageDto = new PageDto<>();
        List<MovieResponseDto> movieList = movieResponseDtoDao.getItemsDto(currentPage, itemsOnPage, parameters);
        pageDto.setCount((long) movieList.size());
        pageDto.setEntities(movieList);
        return pageDto;
    }
}