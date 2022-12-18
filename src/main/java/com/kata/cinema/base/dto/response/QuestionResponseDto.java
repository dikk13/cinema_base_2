package com.kata.cinema.base.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class QuestionResponseDto {

    Long id;
    Integer position;
    String question;

}

