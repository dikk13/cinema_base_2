package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.StudioPerformance;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductionMovieStudioResponseDto implements Serializable {
    private Long id;
    private String name;
    private Long performanceId;
    private String performanceName;

}
