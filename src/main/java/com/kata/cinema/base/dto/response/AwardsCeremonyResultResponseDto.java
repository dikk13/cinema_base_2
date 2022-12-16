package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kata.cinema.base.models.Nomination;
import com.kata.cinema.base.models.enums.NominationStatus;
import lombok.*;

import java.time.LocalDate;

@Data
public class AwardsCeremonyResultResponseDto {
    private final Long id;
    private final Long personId;
    private final String fullName;
    private final String originalFullName;
    private final Long movieId;
    private final String name;
    private final String originalName;
    private final Nomination nomination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDate dateEvent;
    private final Long awardId;
    private final String awardName;
    private final NominationStatus nominationStatus;
}
