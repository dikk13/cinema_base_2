package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.PaginationDtoDao;
import com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.service.dto.ProductionMovieStudioResponseDtoService;
import org.springframework.stereotype.Service;

@Service
public class ProductionMovieStudioResponseDtoServiceImpl extends PaginationDtoServiceImpl<ProductionMovieStudioResponseDto> implements ProductionMovieStudioResponseDtoService {

    public ProductionMovieStudioResponseDtoServiceImpl(PaginationDtoDao<ProductionMovieStudioResponseDto> paginationDtoDao) {
        super(paginationDtoDao);
    }

}
