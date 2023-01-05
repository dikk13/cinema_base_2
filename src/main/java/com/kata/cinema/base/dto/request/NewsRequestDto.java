package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.Rubric;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class NewsRequestDto {
    @NotBlank
    private Rubric rubric;
    @NotBlank
    private String title;
    @NotBlank
    private String htmlBody;

}
