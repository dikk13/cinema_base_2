package com.kata.cinema.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableOnlineMovieRequestDto {
    private Integer rentalPrice;
    private Integer buyPrice;
    private Boolean availablePlus;

}
