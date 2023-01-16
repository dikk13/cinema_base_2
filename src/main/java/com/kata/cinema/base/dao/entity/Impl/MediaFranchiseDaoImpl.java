package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MediaFranchiseDao;
import com.kata.cinema.base.models.MediaFranchise;
import org.springframework.stereotype.Repository;


@Repository
public class MediaFranchiseDaoImpl extends AbstractDaoImpl<Long, MediaFranchise> implements MediaFranchiseDao {

    @Override
    public Boolean existByName(String name) {


        if ((Integer)entityManager.createQuery("select count (m.name) from MediaFranchise m where m.name =: name")
                .setParameter("name", name)
                .getSingleResult() > 1) {
            return true;
        } else {
            return false;
        }
    }
}