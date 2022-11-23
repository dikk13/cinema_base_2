package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.HistoryDao;
import com.kata.cinema.base.dao.entity.HistoryMovieDao;
import com.kata.cinema.base.dao.entity.HistoryPersonDao;
import com.kata.cinema.base.models.History;
import com.kata.cinema.base.models.HistoryMovie;
import com.kata.cinema.base.models.HistoryPerson;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoryServiceImpl extends AbstractServiceImpl<Long, History> implements HistoryService {

    private final HistoryDao historyDao;
    private final HistoryMovieDao historyMovieDao;
    private final HistoryPersonDao historyPersonDao;

    @Autowired
    protected HistoryServiceImpl(HistoryDao historyDao, HistoryMovieDao historyMovieDao, HistoryPersonDao historyPersonDao) {
        super(historyDao);
        this.historyDao = historyDao;
        this.historyMovieDao = historyMovieDao;
        this.historyPersonDao = historyPersonDao;
    }

    @Override
    @Transactional
    public void addToHistoryMovie(HistoryMovie historyMovie) {
        historyMovieDao.create(historyMovie);
    }

    @Override
    @Transactional
    public void addToHistoryPerson(HistoryPerson historyPerson) {
        historyPersonDao.create(historyPerson);
    }

    @Override
    public void deleteHistoryIfPassed30Days() {
        historyDao.deleteHistoryIfPassed30Days();
    }

}
