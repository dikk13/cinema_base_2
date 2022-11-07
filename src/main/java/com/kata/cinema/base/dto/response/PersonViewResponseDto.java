package com.kata.cinema.base.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonViewResponseDto {
    Long id;
    Double height;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    LocalDate birthday;
    String placeBirthday;
    String photoUrl;
    String fullName;
    String originalFullName;
    Long countMovie;
    List<GenreResponseDto> genres;
    //List<ProfessionResponseDto> profession;

}
