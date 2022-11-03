package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.CommentNewsResponseDto;

import java.util.List;

public interface CommentNewsResponseDtoDao {

    List<CommentNewsResponseDto> getCommentNewsResponseDtoListByNewsId(Long newsId);
}
