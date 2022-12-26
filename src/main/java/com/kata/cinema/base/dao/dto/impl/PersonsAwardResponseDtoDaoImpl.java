package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.PersonsAwardResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonsAwardResponseDtoDaoImpl implements PersonsAwardResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AwardResponseDto> getPersonsAwards(Long id) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.AwardResponseDto" +
                        " (a.id, concat(a.person.originalFirstName, ' ', a.person.originalLastName), a.ceremony.dateEvent, a.nominationStatus) " +
                        "from AwardCeremonyResult a where a.person.id = :id", AwardResponseDto.class)
                .setParameter("id", id)
                .getResultList();
    }

}
