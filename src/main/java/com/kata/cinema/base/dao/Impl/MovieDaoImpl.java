package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.MovieDao;
import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MovieDaoImpl extends AbstractDaoImpl<Long, Movie> implements MovieDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<SearchMovieDto> titleMovie(String name) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.search.SearchMovieDto(m.id, m.name)"
                + " from Movie m WHERE m.name LIKE :name", SearchMovieDto.class)
                .setParameter("name", name + "%").setMaxResults(3).getResultList();
    }
}
