package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.dto.response.CastResponseDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link Movie} entity
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


    public MovieViewResponseDto(Long id, String name, String originalName, String countries, LocalDate dateRelease,
                                RARS rars, MPAA mpaa, String description, String previewUrl, Double score, Long countScore) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.countries = countries;
        this.dateRelease = dateRelease;
        this.rars = rars;
        this.mpaa = mpaa;
        this.description = description;
        this.previewUrl = previewUrl;
        this.score = score;
        this.countScore = countScore.intValue();
    }
}