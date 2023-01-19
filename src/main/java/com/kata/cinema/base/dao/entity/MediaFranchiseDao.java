package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.MediaFranchise;

public interface MediaFranchiseDao extends AbstractDao<Long, MediaFranchise> {
    Boolean existByName(String name);
}