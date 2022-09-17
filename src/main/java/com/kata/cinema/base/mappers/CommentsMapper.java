package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.dto.CommentsResponseDto;
import com.kata.cinema.base.models.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CommentsMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "date", target = "dateTime")
    CommentsResponseDto toDTO(Comments comments);

    List<CommentsResponseDto> toDTOList(List<Comments> comments);

    @Mapping(source = "text", target = "text")
    @Mapping(source = "date", target = "date")
    Comments toComments(CommentsRequestDto commentsRequestDto);

}
