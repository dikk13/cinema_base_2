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
    private String text;
    private LocalDateTime date;
}
