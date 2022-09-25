package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;

import java.util.List;

public interface CollectionService extends AbstractService<Long, Collection> {

    public List<SearchCollectionDto> titleCollection();
}
