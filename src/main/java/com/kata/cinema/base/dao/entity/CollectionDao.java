package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;

import java.util.List;

public interface CollectionDao extends AbstractDao<Long, Collection> {

    List<SearchCollectionDto> titleCollection(String name);

}
