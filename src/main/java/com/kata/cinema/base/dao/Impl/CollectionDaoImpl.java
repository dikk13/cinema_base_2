package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.CollectionDao;
import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectionDaoImpl extends AbstractDaoImpl<Long, Collection> implements CollectionDao {


    public List<SearchCollectionDto> titleCollection(String name) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.search.SearchCollectionDto(c.name)"
                + " from Collection c WHERE c.name LIKE :name", SearchCollectionDto.class)
                .setParameter("name", name + "%")
                .setMaxResults(3)
                .getResultList();
    }
}
