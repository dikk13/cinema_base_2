package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Content;
import com.kata.cinema.base.models.enums.ContentType;

public interface ContentService extends AbstractService<Long, Content> {
    String getContentByMovieIdAndContentType(Long movieId, ContentType type);
}
