package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.Movie;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieTicketRequestDto {

    private Long movieId;
    private LocalDate endShowDate;
}
