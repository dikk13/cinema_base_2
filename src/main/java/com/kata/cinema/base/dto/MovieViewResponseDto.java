package com.kata.cinema.base.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link com.kata.cinema.base.models.Movie} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieViewResponseDto implements Serializable {
    private Long id;

    @NotBlank
    private String name;
    private String originalName;

    private String countries;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateRelease;

    private RARS rars;
    private MPAA mpaa;
    private String description;
    private String previewUrl;
    private String genres;

    private Double score;//average score
    private Integer countScore;
    private Integer myScore;//current user score

    private List<CastResponseDto> casts;
}