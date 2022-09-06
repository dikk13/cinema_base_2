package com.kata.cinema.base.dao.Impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@Repository
public abstract class AbstractDaoImpl<PK, E> {


    @PersistenceContext
    private EntityManager entityManager;

    List<E> getAll() {
        return entityManager.createQuery("FROM " + getClass()).getResultList();
    }

    void create(E entity) {
        entityManager.persist(entity);
    }

    void update(E entity) {
        entityManager.merge(entity);
    }

    void delete(E entity) {
        entityManager.remove(entity);
    }

    void deleteById(PK id) {
        entityManager.createQuery("DELETE " + getClass() + " WHERE id = :id")
                .setParameter("id", id).executeUpdate();

    }

    Optional<E> getById(PK id) {
        try {
            return (Optional<E>) Optional.of(entityManager.find(getClass(), id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    boolean existById(PK id) {
        long count = (long) entityManager.createQuery("SELECT count(e) FROM " + getClass() + " e WHERE e.id = : id")
                .setParameter("id", id).getSingleResult();
        return count > 0;
    }


}
