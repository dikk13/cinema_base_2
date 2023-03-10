package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.CommentsRequestDto;
import com.kata.cinema.base.dto.response.CommentsResponseDto;

import com.kata.cinema.base.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CommentsMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "message", target = "message")
    @Mapping(source = "date", target = "date")
    CommentsResponseDto toDTO(Comment comment);

    List<CommentsResponseDto> toDTOList(List<Comment> comments);

    @Mapping(source = "message", target = "message")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "parentId", target = "parentId")
    @Mapping(source = "level", target = "level")
    Comment toComments(CommentsRequestDto commentsRequestDto);

    @Mapping(source = "message", target = "message")
    @Mapping(source = "date", target = "date")
    Comment toCommentsResp(CommentsResponseDto commentsResponseDto);

}
