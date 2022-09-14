package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FolderMovieDaoImpl extends AbstractDaoImpl <Long, FolderMovie> implements FolderMovieDao {


    @Override
    public List<FolderMovie> getFolderMovieListByUserId(Long userId) {
        return entityManager.createQuery("select fm from FolderMovie fm where fm.user.id =: id", FolderMovie.class)
                .setParameter("id", userId)
                .getResultList();
    }

    @Override
    public FolderMovie getFolderMovieById(Long folderMovieId) {
        return entityManager.createQuery("select fm from FolderMovie fm where fm.user.id =: id", FolderMovie.class)
                .setParameter("id", folderMovieId)
                .getSingleResult();
    }

}
