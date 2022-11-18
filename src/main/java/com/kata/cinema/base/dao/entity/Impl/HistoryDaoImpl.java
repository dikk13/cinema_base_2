package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.HistoryDao;
import com.kata.cinema.base.models.History;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDaoImpl extends AbstractDaoImpl<Long, History> implements HistoryDao {
}
