package com.kata.cinema.base.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieRequestDto {
    @NotBlank
    private String name;

    private String countries;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateRelease;

    @NotBlank
    private RARS rars;

    @NotBlank
    private MPAA mpaa;

    @NotBlank
    private int time;

    private String description;

    @NotBlank
    private String originalName;

    private List<Long> genreIds;


}
