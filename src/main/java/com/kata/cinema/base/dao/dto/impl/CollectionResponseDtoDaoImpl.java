package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CollectionResponseDtoDao;
import com.kata.cinema.base.dto.response.CollectionResponseDto;
import com.kata.cinema.base.models.enums.CollectionType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CollectionResponseDtoDaoImpl implements CollectionResponseDtoDao {

    private final EntityManager entityManager;

    public CollectionResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CollectionResponseDto> getCollectionResponseDtoListByType(CollectionType collectionType) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.CollectionResponseDto(" +
                                "c.id, " +
                                "c.name, " +
                                "c.collectionType, " +
                                "(select count (mov) from Movie mov join mov.collections col where col.collectionType = : collectionType), " +
                                "count (fm))" +
                                "from FolderMovie fm join fm.movies m join m.collections c where c.collectionType = : collectionType",
                        CollectionResponseDto.class)
                .setParameter("collectionType", collectionType)
                .getResultList();
    }
}