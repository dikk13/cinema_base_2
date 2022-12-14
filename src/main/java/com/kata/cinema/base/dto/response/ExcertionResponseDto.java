package com.kata.cinema.base.dto.response;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExcertionResponseDto implements Serializable {

    private Long id;

    @NotBlank
    private String description;


}
