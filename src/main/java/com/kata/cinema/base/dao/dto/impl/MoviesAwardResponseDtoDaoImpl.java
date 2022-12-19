package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.MoviesAwardResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MoviesAwardResponseDtoDaoImpl implements MoviesAwardResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AwardResponseDto> getMoviesAwards(Long id) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.AwardResponseDto" +
                        " (a.id, a.movie.name, a.ceremony.dateEvent, a.nominationStatus) " +
                        "from AwardCeremonyResult a where a.movie.id = :id", AwardResponseDto.class)
                .setParameter("id", id)
                .getResultList();
    }

}
