package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AwardCeremonyDao;
import com.kata.cinema.base.models.AwardCeremony;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AwardCeremonyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AwardCeremonyServiceImpl extends AbstractServiceImpl<Long, AwardCeremony> implements AwardCeremonyService {

    private final AwardCeremonyDao awardCeremonyDao;

    protected AwardCeremonyServiceImpl(AwardCeremonyDao awardCeremonyDao, AwardCeremonyDao awardCeremonyDao1) {
        super(awardCeremonyDao);
        this.awardCeremonyDao = awardCeremonyDao1;
    }

    @Override
    @Transactional
    public Long getAwardIdFromCeremonyId(Long ceremonyId) {
        return awardCeremonyDao.getAwardIdFromCeremonyId(ceremonyId);
    }
}
