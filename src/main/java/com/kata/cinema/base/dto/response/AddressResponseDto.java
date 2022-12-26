package com.kata.cinema.base.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private Long id;
    private String street;
    private String city;
}
