package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.CommentNewsResponseDtoDao;
import com.kata.cinema.base.dto.response.CommentNewsResponseDto;
import com.kata.cinema.base.service.dto.CommentNewsResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentNewsResponseDtoServiceImpl implements CommentNewsResponseDtoService {

    private final CommentNewsResponseDtoDao commentNewsResponseDtoDao;


    CommentNewsResponseDtoServiceImpl(CommentNewsResponseDtoDao commentNewsResponseDtoDao) {
        this.commentNewsResponseDtoDao = commentNewsResponseDtoDao;
    }

    @Override
    public List<CommentNewsResponseDto> getCommentNewsResponseDtoListByNewsId(Long newsId) {
        return commentNewsResponseDtoDao.getCommentNewsResponseDtoListByNewsId(newsId);
    }
}
