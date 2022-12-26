package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.dto.response.ReviewResponseDto;
import com.kata.cinema.base.mappers.RedactorCommentsMapper;
import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.service.dto.ReviewResponseDtoService;
import com.kata.cinema.base.service.entity.RedactorCommentsService;
import com.kata.cinema.base.service.entity.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redactor/reviews")
@AllArgsConstructor
public class RedactorReviewsRestController {

    private final ReviewResponseDtoService reviewResponseDtoService;
    private final RedactorCommentsMapper redactorCommentsMapper;
    private final RedactorCommentsService redactorCommentsService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllUnmoderatedReviewsList() {
        return new ResponseEntity<>(reviewResponseDtoService.getAllUnmoderatedReviewsList(), HttpStatus.FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addRedactorComment(@PathVariable Long id, @RequestBody RedactorCommentRequestDto redactorCommentRequestDto) {
        RedactorComment redactorComment = redactorCommentsMapper.toRedactorComments(redactorCommentRequestDto);
        redactorCommentsService.create(redactorComment);
        reviewService.changeModerateFlag(id, redactorComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
