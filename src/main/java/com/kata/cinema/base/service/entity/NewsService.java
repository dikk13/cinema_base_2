package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.News;

public interface NewsService extends AbstractService<Long, News> {
    News getNewsById(Long newsId);
}
