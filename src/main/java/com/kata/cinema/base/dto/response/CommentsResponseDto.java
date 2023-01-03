package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.dto.UserCommentDto;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.TypeRating;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class CommentsResponseDto implements Serializable {
    private Long id;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate date;
    private Boolean isModerate;
    private Integer level;
    private Long parentId;
}