package com.kata.cinema.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kata.cinema.base.models.enums.CharacterType;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.kata.cinema.base.models.MoviePerson} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoviePersonResponseDto implements Serializable {
    @JsonIgnore
    private Long professionId;
    private Long personId;
    private String fullName; //'Person.firstName Person.lastName'
    private  String originalFullName; //'Person.originalFirstName Person.originalLastName'
    private CharacterType type;
    private String nameCharacter; //MoviePerson.nameRole
}