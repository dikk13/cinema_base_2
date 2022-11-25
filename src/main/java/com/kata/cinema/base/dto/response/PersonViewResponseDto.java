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
    private Long id;
    private Double height;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private String placeBirthday;
    private String fullName;
    private String originalFullName;
    private Long countMovie;
    private List<GenreResponseDto> genres;
    private List<ProfessionResponseDto> profession;


    public PersonViewResponseDto(
            Long id, Double height, LocalDate birthday, String placeBirthday
            , String firstName, String lastName
            , String originalFirstName, String originalLastName) {
        this.id = id;
        this.height = height;
        this.birthday = birthday;
        this.placeBirthday = placeBirthday;
        this.fullName = firstName + " " + lastName;
        this.originalFullName = originalFirstName + " " + originalLastName;
    }
}
