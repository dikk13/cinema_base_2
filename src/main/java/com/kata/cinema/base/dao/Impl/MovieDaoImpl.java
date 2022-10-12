package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.MovieDao;
import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDaoImpl extends AbstractDaoImpl<Long, Movie> implements MovieDao {


    public List<SearchMovieDto> titleMovie(String name) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.search.SearchMovieDto(m.id, m.name)"
                        + " from Movie m WHERE m.name LIKE :name", SearchMovieDto.class)
                .setParameter("name", name + "%")
                .setMaxResults(3)
                .getResultList();
    }

    public Optional<Movie> getMovieWithMoviePersonsWithProfessionsAndPersonsByMovieId(Long id) {
        try {
            return Optional.of((Movie) entityManager.createQuery("select movie from Movie movie " +
                            "join fetch movie.moviePerson moviePerson " +
                            "join fetch moviePerson.profession profession " +
                            "join fetch moviePerson.person person " +
                            "where movie.id =: id")
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }



}
