package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AwardDao;
import com.kata.cinema.base.models.Award;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AwardService;
import org.springframework.stereotype.Service;

@Service
public class AwardServiceImpl extends AbstractServiceImpl<Long, Award> implements AwardService {
    protected AwardServiceImpl(AwardDao awardDao) {
        super(awardDao);
    }
}
