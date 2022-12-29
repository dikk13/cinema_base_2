package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;

import java.util.Optional;

public interface MoviePersonService extends AbstractService<Long, MoviePerson> {
    boolean isProfessionIsBeingUsed(Profession profession);

    Optional<MoviePerson> getMoviePersonByMovieIdPersonIdProfessionId(Long movieId, Long personId, Long professionId);

    void updateById(Long id, MoviePerson moviePerson);
}
