package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.AwardsCeremonyResultResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardsCeremonyResultResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class AwardsCeremonyResultResponseDtoDaoImpl implements AwardsCeremonyResultResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AwardsCeremonyResultResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery(" select new com.kata.cinema.base.dto.response.AwardsCeremonyResultResponseDto" +
                                "(a.id, a.person.id, concat(a.person.firstName, ' ', a.person.lastName), " +
                                "concat(a.person.originalFirstName, ' ', a.person.originalLastName), a.movie.id, " +
                                "a.movie.name, a.movie.originalName, a.nomination, a.ceremony.dateEvent, " +
                                "a.ceremony.id, a.nomination.name, a.nominationStatus) from AwardCeremonyResult a" +
                                " where a.ceremony.awards.id = :awardId and a.ceremony.dateEvent = :dateEvent" +
                                " and a.nominationStatus = :nominationStatus"
                        , AwardsCeremonyResultResponseDto.class)
                .setParameter("awardId", parameters.get("awardId"))
                .setParameter("dateEvent", parameters.get("dateEvent"))
                .setParameter("nominationStatus", parameters.get("nominationStatus"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return jpaResultHelper(entityManager.createQuery("select count (a) " +
                "from AwardCeremonyResult a where a.ceremony.awards.id = :awardId and" +
                " a.ceremony.dateEvent = :dateEvent and a.nominationStatus = :nominationStatus"
                , Long.class)
                .setParameter("awardId", parameters.get("awardId"))
                .setParameter("dateEvent", parameters.get("dateEvent"))
                .setParameter("nominationStatus", parameters.get("nominationStatus")))
                .orElse(null);
    }

}
