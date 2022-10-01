package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.CollectionDao;
import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.service.abstracts.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends AbstractServiceImpl<Long, Collection> implements CollectionService {

    private final CollectionDao collectionDao;

    @Autowired
    protected CollectionServiceImpl(AbstractDao<Long, Collection> abstractDao, CollectionDao collectionDao) {
        super(abstractDao);
        this.collectionDao = collectionDao;
    }

    @Override
    public List<SearchCollectionDto> titleCollection(String name) {
        return collectionDao.titleCollection(name);
    }
}