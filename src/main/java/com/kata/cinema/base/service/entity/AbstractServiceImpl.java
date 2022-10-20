package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.service.entity.AbstractService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public abstract class AbstractServiceImpl<PK, E> implements AbstractService<PK, E> {

    private final AbstractDao<PK, E> abstractDao;


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
