package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.FolderPersonResponseDtoDao;
import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FolderPersonResponseDtoDaoImpl implements FolderPersonResponseDtoDao {
    @PersistenceContext
   private EntityManager entityManager;

    @Override
    public List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long id) {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.response.FolderPersonResponseDto" +
                                "(fm.privacy," +
                                "fm.favourites," +
                                "fm.description," +
                                "fm.name," +
                                "fm.id," +
                                "fm.name," +
                                "count (m) from FolderPerson fm join Person m where m.id=:id",
                        FolderPersonResponseDto.class)
                .setParameter("id", id)
                .getResultList();

    }
}