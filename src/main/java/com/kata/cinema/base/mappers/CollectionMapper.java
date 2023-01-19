package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.CollectionRequestDto;
import com.kata.cinema.base.models.AwardCeremony;
import com.kata.cinema.base.models.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface CollectionMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "type", target = "collectionType")
    Collection toCollection(CollectionRequestDto collectionRequestDto);
}


