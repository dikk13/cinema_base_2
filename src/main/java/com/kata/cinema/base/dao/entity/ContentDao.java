package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Content;

public interface ContentDao extends AbstractDao<Long, Content> {
    void deleteById(long id);
}