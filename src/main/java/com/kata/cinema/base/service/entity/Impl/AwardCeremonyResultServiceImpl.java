package com.kata.cinema.base.service.entity.Impl;


import com.kata.cinema.base.dao.entity.AwardCeremonyResultDao;
import com.kata.cinema.base.models.AwardCeremonyResult;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AwardCeremonyResultService;
import org.springframework.stereotype.Service;

@Service
public class AwardCeremonyResultServiceImpl extends AbstractServiceImpl<Long, AwardCeremonyResult> implements AwardCeremonyResultService {
    protected AwardCeremonyResultServiceImpl(AwardCeremonyResultDao awardCeremonyResultDao) {
        super(awardCeremonyResultDao);
    }
}
