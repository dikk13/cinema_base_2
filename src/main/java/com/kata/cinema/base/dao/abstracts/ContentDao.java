package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Content;
import com.kata.cinema.base.models.enums.ContentType;

public interface ContentDao extends AbstractDao<Long, Content> {
    String getContentByMovieIdAndContentType(Long movieId, ContentType type);
}
