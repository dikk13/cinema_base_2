package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.CollectionDao;
import com.kata.cinema.base.dto.search.SearchCollectionDto;
import com.kata.cinema.base.models.Collection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CollectionDaoImpl extends AbstractDaoImpl<Long, Collection> implements CollectionDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<SearchCollectionDto> titleCollection() {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.search.SearchCollectionDto(c.name)"
                + " from Collection c", SearchCollectionDto.class).setMaxResults(3).getResultList();
    }
}
