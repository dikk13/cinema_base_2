package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.HistoryPersonDao;
import com.kata.cinema.base.models.HistoryPerson;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryPersonDaoImpl extends AbstractDaoImpl<Long, HistoryPerson> implements HistoryPersonDao {
}
