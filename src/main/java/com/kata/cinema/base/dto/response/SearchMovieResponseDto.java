package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.CharacterType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SearchMovieResponseDto implements Serializable {
    private Long professionId;
    private Long personId;
    private String fullName; //'Person.firstName Person.lastName'
    private  String originalFullName; //'Person.originalFirstName Person.originalLastName'
    private CharacterType type;
    private String nameCharacter; //MoviePerson.nameRole
    private String photoUrl;
}
