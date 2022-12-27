package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {

    private Long id;
    private Integer position;
    private String question;

    private PageDto<QuestionResponseDto> page;

    public QuestionResponseDto(Long id, Integer position, String question) {
        this.id = id;
        this.position = position;
        this.question = question;
    }
}
