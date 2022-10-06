package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.FolderMovieResponsDtoDao;
import com.kata.cinema.base.dto.FolderMovieResponsDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FolderMovieResponsDtoDaoImpl implements FolderMovieResponsDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.FolderMovieResponsDto(fm.id, fm.category, fm.privacy, fm.name, fm.description) from FolderMovie fm where fm.id =: id", FolderMovieResponsDto.class)
                .setParameter("id", folderMovieId)
                .getSingleResult();
    }

}
