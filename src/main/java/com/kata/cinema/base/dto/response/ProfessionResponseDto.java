package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.Profession;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * A DTO for the {@link Profession} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProfessionResponseDto {

    private Long id;

    @NotBlank
    private String name;

    public ProfessionResponseDto(Profession profession) {
        id = getId();
        name = getName();
    }

}
