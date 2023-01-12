package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.CollectionResponseDtoDao;
import com.kata.cinema.base.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.enums.CollectionType;
import com.kata.cinema.base.service.dto.CollectionResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionResponseDtoServiceImpl implements CollectionResponseDtoService {

    private final CollectionResponseDtoDao collectionResponseDtoDao;

    public CollectionResponseDtoServiceImpl(CollectionResponseDtoDao collectionResponseDtoDao) {
        this.collectionResponseDtoDao = collectionResponseDtoDao;
    }

    @Override
    public List<CollectionResponseDto> getCollectionResponseDtoListByType(CollectionType type) {
        return collectionResponseDtoDao.getCollectionResponseDtoListByType(type);
    }
}
