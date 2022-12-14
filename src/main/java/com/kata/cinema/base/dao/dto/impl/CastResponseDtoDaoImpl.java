package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CastResponseDtoDao;
import com.kata.cinema.base.dto.response.CastResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CastResponseDtoDaoImpl implements CastResponseDtoDao {

    private final EntityManager entityManager;

    public CastResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CastResponseDto> getCastResponseDtoListByMovieId(Long id) {
        return entityManager.createQuery("" +
                        "select distinct new com.kata.cinema.base.dto.response.CastResponseDto(m.id, p.id, p.name) " +
                        "from Movie m join m.moviePerson mp join mp.profession p where m.id =: id", CastResponseDto.class)
                .setParameter("id", id)
                .getResultList();
    }
}
