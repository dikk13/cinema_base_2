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
    EntityManager entityManager;

    @Override
    public List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long id) {
        //return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.response.FolderPersonResponseDto (f.privacy,f.description,f.name,f.favourites, select count (m) from FolderPerson fm join fm.folderPersonsSet m where m.id= : ) from FolderPerson f where f.name=:name",
        // FolderPersonResponseDto.class)

        return entityManager.createQuery(new StringBuilder().append("SELECT new com.kata.cinema.base.dto.response.FolderPersonResponseDto(").append("f.id,").append("f.name,").append("f.description,").append("f.favourites,").append("f.privacy,").append("count (m.id) from FolderPerson fm join fm.folderPersonsSet m where m.id = : id)").toString(), FolderPersonResponseDto.class)
                .setParameter("id", id)
                .getResultList();
    }

}