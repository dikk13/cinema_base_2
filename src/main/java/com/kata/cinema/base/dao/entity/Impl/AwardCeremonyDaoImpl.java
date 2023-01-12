package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AwardCeremonyDao;
import com.kata.cinema.base.models.AwardCeremony;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AwardCeremonyDaoImpl extends AbstractDaoImpl<Long, AwardCeremony> implements AwardCeremonyDao {

    @Override
    @Transactional
    public Long getAwardIdFromCeremonyId(Long ceremonyId) {

        return (Long) entityManager.createQuery("select aw.awards.id from AwardCeremony aw where aw.id =: id")
                .setParameter("id", ceremonyId)
                .getSingleResult();
    }
}
