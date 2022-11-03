//package com.kata.cinema.base.mappers;
//
//import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
//import com.kata.cinema.base.models.News;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.springframework.stereotype.Component;
//
//@Component
//@Mapper(componentModel = "spring")
//public interface NewsBodyMapper {
//
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "rubric", source = "rubric")
//    @Mapping(target = "date", source = "date")
//    @Mapping(target = "title", source = "title")
//    @Mapping(target = "html_body", source = "html_body")
//    @Mapping(target = "questions", source = "questions")
//    NewsBodyResponseDto toNewsBodyResponseDto(News news);
//
//}
