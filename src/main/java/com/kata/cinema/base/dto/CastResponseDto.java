package com.kata.cinema.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CastResponseDto implements Serializable {

    @JsonIgnore
    private Long movieId;
    private Long professionId;
    private String professionName;
    private List<MoviePersonResponseDto> persons;
}
