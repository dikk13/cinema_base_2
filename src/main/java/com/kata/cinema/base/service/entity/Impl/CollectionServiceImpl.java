package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.CollectionDao;
import com.kata.cinema.base.dto.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends AbstractServiceImpl<Long, Collection> implements CollectionService {

    private final CollectionDao collectionDao;

    @Autowired
    protected CollectionServiceImpl(CollectionDao collectionDao) {
        super(collectionDao);
        this.collectionDao = collectionDao;
    }

    @Override
    public List<SearchCollectionDto> titleCollection(String name) {
        return collectionDao.titleCollection(name);
    }

    @Override
    public List<Movie> searchByIds(List<Long> list) {
        return collectionDao.searchByIds(list);
    }
}