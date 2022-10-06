package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoImpl extends AbstractDaoImpl <Long,Genre> implements GenreDao {


}
