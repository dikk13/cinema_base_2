package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.CommentsResponseDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.service.abstracts.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news/{id}/comments")
public class NewsRestController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsMapper commentsMapper;

    @GetMapping("")
    public List<CommentsResponseDto> getCommentsResponseDtoById(@PathVariable("id") long newsId) {
//        return commentsService.getAllComments().stream().map(c -> commentsMapper.toDTO(c)).toList();
        return commentsMapper.toDTOList(commentsService.getAllCommentsByNewsId(newsId));
    }
}
