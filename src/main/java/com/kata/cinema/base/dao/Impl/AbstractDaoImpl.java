package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/*
    Этот класс имеет следующие методы с подавленными предупреждениями:
    * public List<E> getAll();
    * public Optional<E> getById(PK id),
    которые подавляют Unchecked cast сыроого типа в параметризованный.
    Предупреждение возникает из-за вероятности возникновения ClassCastException при попытке приведения элемента листа
    к некому типу данных, являющемуся E - одним из параметров класса.

 */

public abstract class AbstractDaoImpl<PK, E> implements AbstractDao<PK, E> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
    private final Class<?> genericType = (Class<?>) type.getActualTypeArguments()[1];

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        return (List<E>) entityManager.createQuery("FROM " + genericType.getName()).getResultList();
    }

    public void create(E entity) {
        entityManager.persist(entity);
    }

    public void update(E entity) {
        entityManager.merge(entity);
    }

    public void delete(E entity) {
        entityManager.remove(entity);
    }

    public void deleteById(PK id) {
        entityManager.createQuery("DELETE " + genericType.getName() + " WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public Optional<E> getById(PK id) {
        try {
            return (Optional<E>) Optional.of(entityManager.find(genericType, id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public boolean existById(PK id) {
        long count = (long) entityManager.createQuery("SELECT count(e) FROM " + genericType.getName() + " e WHERE e.id = : id")
                .setParameter("id", id).getSingleResult();
        return count > 0;
    }
}
