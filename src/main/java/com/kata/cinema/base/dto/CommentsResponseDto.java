package com.kata.cinema.base.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentsResponseDto {
    private Long id;
    @NotBlank
    private String text;
    private LocalDateTime dateTime;
}
