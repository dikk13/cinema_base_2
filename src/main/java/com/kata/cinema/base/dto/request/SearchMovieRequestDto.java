package com.kata.cinema.base.dto.request;


import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SearchMovieRequestDto {

    private Long professionId;

    private List<Long> personsId;
}
