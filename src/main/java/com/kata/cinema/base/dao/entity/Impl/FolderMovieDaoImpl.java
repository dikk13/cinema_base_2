package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

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
        return jpaResultHelper(entityManager.createQuery("select fm from FolderMovie fm where fm.user.id =: id", FolderMovie.class)
                .setParameter("id", folderMovieId))
                .orElse(null);
    }

}
