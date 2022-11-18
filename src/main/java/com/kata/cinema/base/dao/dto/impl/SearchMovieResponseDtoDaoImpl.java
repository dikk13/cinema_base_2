package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.SearchMovieResponseDtoDao;
import com.kata.cinema.base.dto.response.SearchMovieResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class SearchMovieResponseDtoDaoImpl implements SearchMovieResponseDtoDao {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public List<SearchMovieResponseDto> getItemsDto(Integer currentPage,
                                                    Integer itemsOnPage,
                                                    Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT NEW com.kata.cinema.base.dto.response.SearchMovieResponseDto(mp.profession.id, " +
                        "mp.person.id, CONCAT (mp.person.firstName, ' ', mp.person.lastName), " +
                        "CONCAT (mp.person.originalFirstName, ' ', mp.person.originalLastName), mp.type, mp.nameRole, p.photoUrl) " +
                        "FROM MoviePerson mp JOIN Person p ON mp.person.id = p.id WHERE mp.profession.id = :professionId AND mp.movie.id = :movieId AND mp.person.id IN (:personsId)", SearchMovieResponseDto.class)
                .setParameter("professionId", parameters.get("professionId"))
                .setParameter("personsId", parameters.get("personsId"))
                .setParameter("movieId", parameters.get("movieId"))
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT COUNT (mp) FROM MoviePerson mp WHERE mp.profession.id = :professionId AND mp.movie.id = :movieId AND mp.person.id IN (:personsId)", Long.class)
                .setParameter("professionId", parameters.get("professionId"))
                .setParameter("personsId", parameters.get("personsId"))
                .setParameter("movieId", parameters.get("movieId")).getSingleResult();
    }
}
