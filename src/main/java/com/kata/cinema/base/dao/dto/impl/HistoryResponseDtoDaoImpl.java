package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.HistoryResponseDtoDao;
import com.kata.cinema.base.dto.response.HistoryResponseDto;
import com.kata.cinema.base.models.enums.TypeContent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HistoryResponseDtoDaoImpl implements HistoryResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<HistoryResponseDto> getHistoryResponseDtoListByUserId(Long userId) {
        return entityManager.createQuery("" +
                "select new com.kata.cinema.base.dto.response.HistoryResponseDto(" +
                "h.id, h.date, hm.movie.id, hp.person.id, " +
                "(select c.contentUrl from HistoryMovie hm left join Content c on hm.movie.id = c.movie.id where c.typeContent =: previewContent and hm.id = h.id), " +
                "h.type) " +
                "from History h left join HistoryMovie hm on hm.id = h.id left join HistoryPerson hp on hp.id = h.id " +
                "where h.user.id =: userId", HistoryResponseDto.class)
                .setParameter("userId", userId)
                .setParameter("previewContent", TypeContent.PREVIEW)
                .getResultList();
    }
}
