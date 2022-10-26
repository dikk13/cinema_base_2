package com.kata.cinema.base.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserRegistrationRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Size(min = 6, max = 20)
    private String confirmPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    public UserRegistrationRequestDto (String email, String firstName, String lastName, String login, String password, String confirmPassword, LocalDate birthday) {
        this.email = email;
        this.first_name = firstName;
        this.last_name = lastName;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.birthday = birthday;
    }
}
