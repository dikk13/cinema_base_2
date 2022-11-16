package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;

public interface MoviePersonDao extends AbstractDao<Long, MoviePerson> {
    boolean isProfessionIsBeingUsed(Profession profession);
}
