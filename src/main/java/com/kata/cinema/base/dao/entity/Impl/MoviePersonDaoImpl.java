package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import org.springframework.stereotype.Repository;

@Repository
public class MoviePersonDaoImpl extends AbstractDaoImpl<Long, MoviePerson> implements MoviePersonDao {
}
