package com.kata.cinema.base.dto.request;

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
    private Long id;
    @NotBlank
    private String message;
    private Integer level;
    private Long parentId;
    private LocalDateTime date;

    public CommentsRequestDto(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }
}
