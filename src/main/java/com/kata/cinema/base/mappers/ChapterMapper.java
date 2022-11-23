package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.ChapterResponseDto;
import com.kata.cinema.base.models.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ChapterMapper {

    List<ChapterResponseDto> toChapterResponseDtoList(List<Chapter> chapters);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    ChapterResponseDto toChapterResponseDto(Chapter chapter);
}
