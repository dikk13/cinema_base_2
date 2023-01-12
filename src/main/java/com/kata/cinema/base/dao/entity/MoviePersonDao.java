package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;

import java.util.Optional;

public interface MoviePersonDao extends AbstractDao<Long, MoviePerson> {
    boolean isProfessionIsBeingUsed(Profession profession);

    Optional<MoviePerson> getMoviePersonByMovieIdPersonIdProfessionId(Long movieId, Long personId, Long professionId);
}
