package com.kata.cinema.base.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AuthRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public AuthRequestDto (String email, String password) {
        this.username = email;
        this.password = password;
    }
}
