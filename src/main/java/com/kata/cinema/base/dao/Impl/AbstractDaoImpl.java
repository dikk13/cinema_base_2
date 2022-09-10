package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@Repository
public abstract class AbstractDaoImpl<PK, E> implements AbstractDao<PK, E> {


    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAll() {
        return (List<E>) entityManager.createQuery("FROM " + getClass()).getResultList();
    }


    @Override
    public void create (E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(PK id) {
        entityManager.createQuery("DELETE " + getClass() + " WHERE id = :id")
                .setParameter("id", id).executeUpdate();

    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<E> getById(PK id) {
        try {
            return (Optional<E>) Optional.of(entityManager.find(getClass(), id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existById(PK id) {
        long count = (long) entityManager.createQuery("SELECT count(e) FROM " + getClass() + " e WHERE e.id = : id")
                .setParameter("id", id).getSingleResult();
        return count > 0;
    }
}
