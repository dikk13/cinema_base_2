package com.kata.cinema.base.service;

import com.kata.cinema.base.dao.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AbstractServiceImpl<PK, E>  implements AbstractService{

    private final AbstractDao abstractDao;

    @Autowired
    public AbstractServiceImpl(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }


    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteById(Object id) {

    }

    @Override
    public Optional getById(Object id) {
        return Optional.empty();
    }

    @Override
    public boolean existById(Object id) {
        return false;
    }
}
