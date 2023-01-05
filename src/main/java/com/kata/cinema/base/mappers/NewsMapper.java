package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.NewsRequestDto;
import com.kata.cinema.base.models.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(source = "rubric", target = "rubric")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "htmlBody", target = "htmlBody")
    News toNews(NewsRequestDto newsRequestDto);

}
