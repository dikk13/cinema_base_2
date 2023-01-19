package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface CollectionDao extends AbstractDao<Long, Collection> {

    List<SearchCollectionDto> titleCollection(String name);

    List<Movie> searchByIds(List<Long> list);
}
