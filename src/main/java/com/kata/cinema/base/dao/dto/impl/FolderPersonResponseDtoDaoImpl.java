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
    public List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long userId) {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.response.FolderPersonResponseDto" +
                                "(f.id," +
                                "f.name," +
                                "f.description," +
                                "f.favourites," +
                                "f.privacy," +
                                "count (p)) " +
                                "FROM FolderPerson f join Person p ON p.id=f.person.id " +
                                "join User u ON u.id=f.user.id WHERE f.user.id=:userId",
                        FolderPersonResponseDto.class)
                .setParameter("userId", userId)
                .getResultList();

    }
}

