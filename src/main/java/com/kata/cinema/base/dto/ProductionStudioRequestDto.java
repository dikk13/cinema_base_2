package com.kata.cinema.base.dto;

import com.kata.cinema.base.models.ProductionStudio;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link ProductionStudio} entity
 */
@Data
public class ProductionStudioRequestDto implements Serializable {
    private final String name;
    private final String description;
    private final LocalDate dateFoundation;
}