package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.SearchMovieResponseDtoDao;
import com.kata.cinema.base.dto.response.SearchMovieResponseDto;
import com.kata.cinema.base.service.dto.SearchMovieResponseDtoService;
import org.springframework.stereotype.Service;

@Service
public class SearchMovieResponseDtoServiceImpl extends PaginationDtoServiceImpl<SearchMovieResponseDto> implements SearchMovieResponseDtoService {

    public SearchMovieResponseDtoServiceImpl(SearchMovieResponseDtoDao searchMovieResponseDtoDao) {
        super(searchMovieResponseDtoDao);
    }

}
