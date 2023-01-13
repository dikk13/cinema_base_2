package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ContentDao;
import com.kata.cinema.base.models.Content;
import com.kata.cinema.base.service.entity.AbstractService;
import com.kata.cinema.base.service.entity.ContentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService, AbstractService<Long, Content> {
    private final ContentDao contentDao;

    public ContentServiceImpl(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    @Override
    public List<Content> getAll() {
        return null;
    }

    @Override
    public void create(Content entity) {

    }

    @Override
    public void update(Content entity) {

    }

    @Override
    public void delete(Content entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Content> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }
}
