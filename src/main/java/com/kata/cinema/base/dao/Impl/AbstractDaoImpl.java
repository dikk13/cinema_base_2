package com.kata.cinema.base.dao.Impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        String hqlGetId = "FROM " + getClass() + " WHERE id: id";
        try {
            Optional.of(entityManager.find(hqlGetId.getClass(), id));
        } catch (Exception ex) {
            Optional.empty();
        }
        return Optional.empty();
    }

    boolean existById(PK id) {
        long count = (long) entityManager.createQuery("SELECT count(e) FROM " + getClass() + " e WHERE e.id = : id")
                .setParameter("id", id).getSingleResult();
        return count > 0;
    }


}
