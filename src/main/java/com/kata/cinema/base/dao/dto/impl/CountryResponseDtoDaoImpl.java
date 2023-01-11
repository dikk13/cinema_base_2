package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CountryResponseDtoDao;
import com.kata.cinema.base.dto.response.CountryResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CountryResponseDtoDaoImpl implements CountryResponseDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<CountryResponseDto> getAllCountryResponseDto() {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.CountryResponseDto(" +
                "c.id, c.name) " +
                "from Country c", CountryResponseDto.class).getResultList();
    }

    @Override
    public List<CountryResponseDto> getListCountryResponseDtoByName(String filterPattern) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.CountryResponseDto(" +
                "c.id, c.name) " +
                "from Country c where c.name like :filterPattern", CountryResponseDto.class)
                .setParameter("filterPattern", "%" + filterPattern + "%")
                .getResultList();
    }
}
