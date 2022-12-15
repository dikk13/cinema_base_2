package com.kata.cinema.base.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequestDto {

    private String street;
    private String city;
}
