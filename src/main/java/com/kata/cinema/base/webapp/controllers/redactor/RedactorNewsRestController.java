package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.dto.response.NewsResponseDto;
import com.kata.cinema.base.mappers.RedactorCommentsMapper;
import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.service.dto.NewsResponseDtoService;
import com.kata.cinema.base.service.entity.NewsService;
import com.kata.cinema.base.service.entity.RedactorCommentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redactor/news")
@AllArgsConstructor
public class RedactorNewsRestController {

    private final NewsResponseDtoService newsResponseDtoService;
    private final RedactorCommentsMapper redactorCommentsMapper;
    private final RedactorCommentsService redactorCommentsService;
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsResponseDto>> getAllUnmoderatedNewsList() {
        return new ResponseEntity<>(newsResponseDtoService.getAllUnmoderatedNewsList(), HttpStatus.FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addRedactorComment(@PathVariable Long id, @RequestBody RedactorCommentRequestDto redactorCommentRequestDto) {
        RedactorComment redactorComment = redactorCommentsMapper.toRedactorComments(redactorCommentRequestDto);
        redactorCommentsService.create(redactorComment);
        newsService.changeModerateFlag(id, redactorComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
