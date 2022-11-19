package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.History;

public interface HistoryDao extends AbstractDao<Long, History>{
    void deleteHistoryIfPassed30Days();
}
