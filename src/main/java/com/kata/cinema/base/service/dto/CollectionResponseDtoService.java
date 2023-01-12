package com.kata.cinema.base.service.dto;


import com.kata.cinema.base.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.enums.CollectionType;

import java.util.List;

public interface CollectionResponseDtoService {

    List<CollectionResponseDto> getCollectionResponseDtoListByType(CollectionType type);

}
