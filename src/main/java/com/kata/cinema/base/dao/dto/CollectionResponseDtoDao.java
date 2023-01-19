package com.kata.cinema.base.dao.dto;


import com.kata.cinema.base.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.enums.CollectionType;

import java.util.List;

public interface CollectionResponseDtoDao {

    List<CollectionResponseDto> getCollectionResponseDtoListByType(CollectionType type);
}
