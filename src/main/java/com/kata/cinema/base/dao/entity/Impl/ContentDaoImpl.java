package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ContentDao;
import com.kata.cinema.base.models.Content;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository

public class ContentDaoImpl extends AbstractDaoImpl<Long, Content> implements ContentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteById(long id) {
        try{
            entityManager.createQuery("delete from Content c where c.id =:id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}