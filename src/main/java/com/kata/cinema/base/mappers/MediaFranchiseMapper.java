package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.MediaFranchiseRequestDto;
import com.kata.cinema.base.models.MediaFranchise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MediaFranchiseMapper {
    @Mapping(source = "name", target = "name")
    MediaFranchise toMediaFranchise(MediaFranchiseRequestDto mediaFranchiseRequestDto);
}