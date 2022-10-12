package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.ContentDao;
import com.kata.cinema.base.models.Content;
import com.kata.cinema.base.models.enums.ContentType;
import com.kata.cinema.base.service.abstracts.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends AbstractServiceImpl<Long, Content> implements ContentService {

    @Autowired
    private ContentDao contentDao;

    protected ContentServiceImpl(AbstractDao<Long, Content> abstractDao) {
        super(abstractDao);
    }

    @Override
    public String getContentByMovieIdAndContentType(Long movieId, ContentType type) {
        return contentDao.getContentByMovieIdAndContentType(movieId, type);
    }
}
