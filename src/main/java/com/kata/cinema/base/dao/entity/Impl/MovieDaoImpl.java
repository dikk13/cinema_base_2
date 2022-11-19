package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieDao;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDaoImpl extends AbstractDaoImpl<Long, Movie> implements MovieDao {

    public List<SearchMovieDto> titleMovie(String name) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.SearchMovieDto(m.id, m.name)"
                        + " from Movie m WHERE m.name LIKE :name", SearchMovieDto.class)
                .setParameter("name", name + "%")
                .setMaxResults(3)
                .getResultList();
    }

}
