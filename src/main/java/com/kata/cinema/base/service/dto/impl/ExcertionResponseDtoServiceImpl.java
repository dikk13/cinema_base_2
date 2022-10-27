package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.ExcertionResponseDtoDao;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.dto.ExcertionResponseDtoService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ExcertionResponseDtoServiceImpl extends PaginationDtoServiceImpl<ExcertionResponseDto> implements ExcertionResponseDtoService {

    private final PersonService personService;
    private final MovieService movieService;

    public ExcertionResponseDtoServiceImpl(ExcertionResponseDtoDao excertionResponseDtoDao, PersonService personService, MovieService movieService) {
        super(excertionResponseDtoDao);
        this.personService = personService;
        this.movieService = movieService;
    }

    @Override
    public Person findPersonById(long id) {
        Optional<Person> person = personService.getById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new NullPointerException("Пользователь не найден");
        }
    }

    @Override
    public Movie findMovieById(long id) {
        Optional<Movie> movie = movieService.getById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new NullPointerException("Фильм не найден");
        }
    }
}