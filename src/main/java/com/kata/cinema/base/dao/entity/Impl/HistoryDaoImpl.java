package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.HistoryDao;
import com.kata.cinema.base.models.History;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class HistoryDaoImpl extends AbstractDaoImpl<Long, History> implements HistoryDao {

    @Override
    @Transactional
    public void deleteHistoryIfPassed30Days() {
        LocalDateTime nowMinus30Days = LocalDateTime.now().minusDays(30);

        entityManager.createQuery("delete from History h where h.date < :date")
                .setParameter("date", nowMinus30Days)
                .executeUpdate();

    }
}
