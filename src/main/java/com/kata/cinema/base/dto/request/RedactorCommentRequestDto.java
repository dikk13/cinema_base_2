package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.RedactorStatus;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RedactorCommentRequestDto {

    private String comment;

    @NotNull
    private RedactorStatus status;

}
