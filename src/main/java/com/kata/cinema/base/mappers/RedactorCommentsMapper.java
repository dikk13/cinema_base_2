package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.RedactorComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RedactorCommentsMapper {

    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "status", target = "status")
    RedactorComment toRedactorComments(RedactorCommentRequestDto redactorCommentRequestDto);

}
