package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.HistoryDao;
import com.kata.cinema.base.models.History;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class HistoryDaoImpl extends AbstractDaoImpl<Long, History> implements HistoryDao {

    @Override
    @Transactional
    public void deleteHistoryIfPassed30Days() {
        LocalDateTime now = LocalDateTime.now();

        List<History> historyList = entityManager.createQuery("" +
                "select h " +
                "from History h", History.class).getResultList();

        for (History history : historyList) {

            long days = ChronoUnit.DAYS.between(history.getDate(), now);

            if (days >= 30) {
                entityManager.createQuery("" +
                                "delete from History h where h.id =: historyId")
                        .setParameter("historyId", history.getId())
                        .executeUpdate();
            }
        }
    }
}
