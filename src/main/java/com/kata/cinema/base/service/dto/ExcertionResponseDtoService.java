package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Person;

public interface ExcertionResponseDtoService extends PaginationDtoService<ExcertionResponseDto>{

    Person findPersonById(long id);

    Movie findMovieById(long id);
}
