package com.kata.cinema.base.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PasswordChangeRequestDto {
    private String oldPassword;
    private String newPassword;
}
