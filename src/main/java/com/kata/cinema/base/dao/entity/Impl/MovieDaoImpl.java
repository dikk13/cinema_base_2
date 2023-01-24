package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieDao;
import com.kata.cinema.base.dto.MovieReleaseAndTimeDto;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MovieDaoImpl extends AbstractDaoImpl<Long, Movie> implements MovieDao {
    @Override
    public List<SearchMovieDto> titleMovie(String name) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.SearchMovieDto(m.id, m.name)"
                        + " from Movie m WHERE m.name LIKE :name", SearchMovieDto.class)
                .setParameter("name", name + "%")
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    public List<MovieReleaseAndTimeDto> movieRelease(LocalDate date) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.MovieReleaseAndTimeDto(m.id, m.dateRelease, m.time)"
                        + "from Movie m WHERE m.dateRelease =:date", MovieReleaseAndTimeDto.class)
                .setParameter("date", date)
                .getResultList();
    }
}
