package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.models.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
@Repository
public class GenreDaoImpl implements GenreDao{


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Genre> getAll() {
        return entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public void create(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public void update(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public void delete(Genre genre) {
        entityManager.remove(genre);
    }

    @Override
    public void deleteById(Long id) {
       Query query = entityManager.createQuery("DELETE  from Genre g  WHERE g.id = :id ");
                query.setParameter("id", id);
                query.executeUpdate();
    }

    @Override
    public Optional<Genre> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }
}
