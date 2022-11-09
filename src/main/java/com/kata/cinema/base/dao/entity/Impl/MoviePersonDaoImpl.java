package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;
import org.springframework.stereotype.Repository;

@Repository
public class MoviePersonDaoImpl extends AbstractDaoImpl<Long, MoviePerson> implements MoviePersonDao {

    public MoviePerson getMoviePersonByProfession(Profession profession) {
        return (MoviePerson) entityManager.createQuery("select m from MoviePerson m " +
                        "where m.profession = :profession")
                .setParameter("profession", profession)
                .getSingleResult();
    }
}
