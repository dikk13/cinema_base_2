package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MediaFranchiseDao;
import com.kata.cinema.base.models.MediaFranchise;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MediaFranchiseDaoImpl extends AbstractDaoImpl<Long, MediaFranchise> implements MediaFranchiseDao {

    @Override
    @Transactional
    public Boolean existByName(String name) {


        if (entityManager.createQuery("select m.name from MediaFranchise m where m.name =: name")
                .setParameter("name", name)
                .getSingleResult() != null) {
            return true;
        } else {
            return false;
        }
    }
}