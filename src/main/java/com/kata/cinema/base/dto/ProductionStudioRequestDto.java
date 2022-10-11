package com.kata.cinema.base.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductionStudioRequestDto implements Serializable {
    private String name;
    private String description;
    private LocalDate dateFoundation;
}
