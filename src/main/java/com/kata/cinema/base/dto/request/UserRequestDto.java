package com.kata.cinema.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private String birthday; // должна передаваться в формате ISO_LOCAL_DATE (YYYY-MM-DD)

}
