package com.kata.cinema.base.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDto {
    @NotBlank
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Double height;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private String placeBirthday;

    private String originalName;

    private String originalLastName;
}
