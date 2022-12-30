package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class MoviePersonDaoImpl extends AbstractDaoImpl<Long, MoviePerson> implements MoviePersonDao {

    public boolean isProfessionIsBeingUsed(Profession profession) {
        return (boolean) entityManager.createQuery("select count(m) > 0 from MoviePerson m " +
                        "where m.profession = :profession")
                .setParameter("profession", profession)
                .getSingleResult();
    }

    @Override
    public Optional<MoviePerson> getMoviePersonByMovieIdPersonIdProfessionId (Long movieId, Long personId, Long professionId) {
        return jpaResultHelper(entityManager.createQuery("select ps from MoviePerson ps " +
                                "where ps.movie.id = :movieId and ps.person.id = :personId and ps.profession.id = :professionId",
                        MoviePerson.class)
                .setParameter("movieId", movieId)
                .setParameter("personId", personId)
                .setParameter("professionId", professionId));
    }
}
