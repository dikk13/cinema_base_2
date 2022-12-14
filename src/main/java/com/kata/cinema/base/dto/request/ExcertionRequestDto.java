package com.kata.cinema.base.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExcertionRequestDto {

    @NotBlank
    private String description;

}
