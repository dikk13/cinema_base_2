package com.kata.cinema.base.dto;

import com.kata.cinema.base.models.StudioPerformance;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductionMovieStudioResponseDto implements Serializable {
    private final Long id;
    private final String name;
    private final StudioPerformance performance;
}
