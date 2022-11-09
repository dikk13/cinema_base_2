package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;

public interface MoviePersonService extends AbstractService<Long, MoviePerson> {
    MoviePerson getMoviePersonByProfession(Profession profession);
}
