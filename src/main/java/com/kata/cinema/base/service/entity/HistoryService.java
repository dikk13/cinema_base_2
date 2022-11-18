package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.History;
import com.kata.cinema.base.models.HistoryMovie;
import com.kata.cinema.base.models.HistoryPerson;

public interface HistoryService extends AbstractService<Long, History> {
    void addToHistoryMovie(HistoryMovie historyMovie);
    void addToHistoryPerson(HistoryPerson historyPerson);
}
