package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.NominationStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AwardResponseDto {
    private final Long id;
    private final String name;
    private final LocalDate date;
    private final NominationStatus nominationStatus;
}

