package com.kata.cinema.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class SearchPersonDto {

    private Long id;
    private String photoUrl;
    private String fullName; // - конкатенация имени и фамилии
    private String originalFullName;  //- конкатенация оригинальной имени и фамилии
    private LocalDate birthday;

    public SearchPersonDto(Long id, String fullName, String originalFullName) {
        this.id = id;
        this.fullName = fullName;
        this.originalFullName = originalFullName;
    }
}
