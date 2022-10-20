package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;

import java.util.List;

public interface CollectionService extends AbstractService<Long, Collection> {

    List<SearchCollectionDto> titleCollection(String name);
}
