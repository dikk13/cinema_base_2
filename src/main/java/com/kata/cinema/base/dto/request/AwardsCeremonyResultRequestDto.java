package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.NominationStatus;
import lombok.*;

@Data
public class AwardsCeremonyResultRequestDto {
    private final Long personId;
    private final Long movieId;
    private final Long nominationId;
    private final NominationStatus nominationStatus;
}
