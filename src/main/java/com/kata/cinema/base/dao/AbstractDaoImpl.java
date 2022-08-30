package com.kata.cinema.base.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class AbstractDaoImpl <PK, E> implements  AbstractDao{

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
