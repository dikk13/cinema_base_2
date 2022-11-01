package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.models.enums.Rubric;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * A DTO for the {@link com.kata.cinema.base.models.News} entity
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsBodyResponseDto implements Serializable {

    private Long id;

    private Rubric rubric;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDateTime date;

    private String title;

    private String htmlBody;

    private Integer countComment;

    private String authorName;

    public NewsBodyResponseDto(Long id, Rubric rubric, LocalDateTime date,
                               String title, String htmlBody,
                               Long countComment, String authorName) {
        this.id = id;
        this.rubric = rubric;
        this.date = date;
        this.title = title;
        this.htmlBody = htmlBody;
        this.countComment = countComment.intValue();
        this.authorName = authorName;
    }
}
