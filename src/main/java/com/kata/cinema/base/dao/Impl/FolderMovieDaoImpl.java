package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FolderMovieDaoImpl implements FolderMovieDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<FolderMovie> getFolderMovieListByUserId(Long UserId) {
        TypedQuery <FolderMovie> query = entityManager.createQuery("select fm from FolderMovie fm where fm.user.id =: id", FolderMovie.class);
        query.setParameter("id", UserId);
        return query.getResultList();
    }

    @Override
    public FolderMovie getFolderMovieById(Long folderMovieId) {
        TypedQuery <FolderMovie> query = entityManager.createQuery("select fm from FolderMovie fm where fm.id =: id", FolderMovie.class);
        query.setParameter("id", folderMovieId);
        return query.getSingleResult();
    }
}
