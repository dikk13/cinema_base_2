package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FolderMovieDaoImpl <PK, E> extends AbstractDaoImpl <PK, E> implements FolderMovieDao <PK, E> {

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<E> getFolderMovieListByUserId(PK UserId) {
        TypedQuery <FolderMovie> query = entityManager.createQuery("select fm from FolderMovie fm where fm.user.id =: id", FolderMovie.class);
        query.setParameter("id", UserId);
        return (List<E>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public E getFolderMovieById(PK folderMovieId) {
        TypedQuery <FolderMovie> query = entityManager.createQuery("select fm from FolderMovie fm where fm.id =: id", FolderMovie.class);
        query.setParameter("id", folderMovieId);
        return (E) query.getSingleResult();
    }
}
