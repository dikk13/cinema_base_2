package com.kata.cinema.base.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserRegistrationRequestDto {

    private String username;
    private String password;

    public UserRegistrationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
