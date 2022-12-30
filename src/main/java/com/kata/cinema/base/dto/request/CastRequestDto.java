package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CastRequestDto {
    private Long professionId;
    private CharacterType type;
    private String nameCharacter;
    private Long personId;
}
