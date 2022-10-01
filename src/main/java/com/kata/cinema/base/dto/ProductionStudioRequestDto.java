package com.kata.cinema.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductionStudioRequestDto implements Serializable {
    private final String name;
    private final String description;
    private final LocalDate dateFoundation;
}
