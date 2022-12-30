package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AwardCeremonyDao;
import com.kata.cinema.base.models.AwardCeremony;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AwardCeremonyService;
import org.springframework.stereotype.Service;

@Service
public class AwardCeremonyServiceImpl extends AbstractServiceImpl<Long, AwardCeremony> implements AwardCeremonyService {
    protected AwardCeremonyServiceImpl(AwardCeremonyDao awardCeremonyDao) {
        super(awardCeremonyDao);
    }
}
