package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.dto.UserCommentDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.TypeRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.kata.cinema.base.models.Comment} entity
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentNewsResponseDto implements Serializable {

    private Long id;
    private String message;
    private Long parentId;
    private Integer level;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate date;
    private Integer rating;
    private UserCommentDto user;


    public CommentNewsResponseDto(Long id, String message,
                                  Long parentId, int level,
                                  LocalDateTime date, User user, Integer rating) {
        this.id = id;
        this.message = message;
        this.parentId = parentId;
        this.level = level;
        this.date = date.toLocalDate();
        this.user = new UserCommentDto(
                user.getId(), user.getEmail(), user.getAvatarUrl()
        );
    }

}

