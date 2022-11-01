package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.dto.UserCommentDto;
import com.kata.cinema.base.models.enums.TypeRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentNewsResponseDto implements Serializable {

    private Long id;
    private String message;
    private Integer level;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate date;
    private TypeRating rating;
    private UserCommentDto user;

}

