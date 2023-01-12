package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.RedactorComment;

public interface NewsService extends AbstractService<Long, News> {

    News getNewsById(Long newsId);

    void changeModerateFlag(Long id, RedactorComment redactorComment);

}
