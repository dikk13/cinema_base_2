package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.CommentNewsResponseDto;

import java.util.List;

public interface CommentNewsResponseDtoService {
    List<CommentNewsResponseDto> getCommentNewsResponseDtoListByNewsId(Long newsId);
}
