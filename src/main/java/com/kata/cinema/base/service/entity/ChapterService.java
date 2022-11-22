package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Chapter;

public interface ChapterService extends AbstractService<Long, Chapter> {
    void updateById(Long id, String name);
}
