package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.MediaFranchise;

public interface MediaFranchiseService extends AbstractService<Long, MediaFranchise> {
    Boolean existByName(String name);
}