package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.FolderMovieResponsDtoDao;
import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FolderMovieResponsDtoDaoImpl implements FolderMovieResponsDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.FolderMovieResponsDto(fm.id, fm.category, fm.privacy, fm.name, fm.description) from FolderMovie fm where fm.id =: id", FolderMovieResponsDto.class)
                .setParameter("id", folderMovieId)
                .getSingleResult();
    }
    @Override
    public List<FolderResponseDto> getFolderByUser(Long userId) {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.response.FolderResponseDto(f.id,f.name,f.category,(select count (m) from FolderMovie fm join fm.movies m where f.user.id = : userId)) from FolderMovie f  where f.user.id = : userId", FolderResponseDto.class)
                .setParameter("userId", userId)
                .getResultList();
    }

}
