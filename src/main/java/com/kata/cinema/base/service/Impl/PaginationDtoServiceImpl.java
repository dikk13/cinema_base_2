package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.PaginationDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.service.abstracts.PaginationDtoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class PaginationDtoServiceImpl<T> implements PaginationDtoService<T> {


    private final PaginationDtoDao<T> paginationDtoDao;


    public PaginationDtoServiceImpl(PaginationDtoDao<T> paginationDtoDao) {
        this.paginationDtoDao = paginationDtoDao;
    }

    @Override
    public PageDto<T> getPageDto(Integer currentPage, Integer itemsOnPage) {
        return new PageDto<>(paginationDtoDao.getResultTotal(new HashMap<>()), paginationDtoDao.getItemsDto(currentPage, itemsOnPage, new HashMap<>()));
    }

    @Override
    public PageDto<T> getPageDtoWithParameters(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return new PageDto<>(paginationDtoDao.getResultTotal(parameters),paginationDtoDao.getItemsDto(currentPage, itemsOnPage, parameters));
    }
}
