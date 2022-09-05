package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public abstract class AbstractServiceImpl<PK, E> {

    private final AbstractDao<PK, E> abstractDao;

    @Autowired
    protected AbstractServiceImpl(AbstractDao<PK, E> abstractDao) {
        this.abstractDao = abstractDao;
    }


    @Transactional
    public List<E> getAll() {
        return abstractDao.getAll();
    }

    @Transactional
    public void create(E entity) {
        abstractDao.create(entity);
    }

    @Transactional
    public void update(E entity) {
        abstractDao.update(entity);
    }

    @Transactional
    public void delete(E entity) {
        abstractDao.delete(entity);
    }

    @Transactional
    public void deleteById(PK id) {
        abstractDao.deleteById(id);
    }
    @Transactional
    public Optional<E> getById(PK id) {
        return abstractDao.getById(id);
    }

    @Transactional
    public boolean existById(PK id) {
        return abstractDao.existById(id);
    }
}
