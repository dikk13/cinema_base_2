package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.MoviePersonResponseDtoDao;
import com.kata.cinema.base.dto.response.MoviePersonResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MoviePersonResponseDtoDaoImpl implements MoviePersonResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    //TODO метод не реализован, удалить если не используется
    @Override
    public List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id) {
        return entityManager.createQuery("" +
                "select new com.kata.cinema.base.dto.response.MoviePersonResponseDto(" +
                "mp.profession.id, p.id, " +
                "concat(p.firstName, ' ', p.lastName), " +
                "concat(p.originalFirstName, ' ', p.originalLastName)," +
                "mp.type, mp.nameRole) " +
                "from MoviePerson mp left join Person p on p.id = mp.person.id where mp.movie.id =: movieId", MoviePersonResponseDto.class)
                .setParameter("movieId", id)
                .getResultList();
    }
}
