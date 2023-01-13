package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ContentDao;
import com.kata.cinema.base.models.Content;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends AbstractServiceImpl<Long,Content> implements ContentService {

    private final ContentDao contentDao;

    public ContentServiceImpl(ContentDao contentDao) {
        super(contentDao);
        this.contentDao = contentDao;
    }
}
