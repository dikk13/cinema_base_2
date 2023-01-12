package com.kata.cinema.base.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentsRequestDto {


    @NotBlank
    private String message;
    private Long parentId;
    private Integer level;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime date;

    public CommentsRequestDto(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }
}
