package com.kata.cinema.base.dto.response;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CountryResponseDto implements Serializable {
    Long id;
    String name;

}
