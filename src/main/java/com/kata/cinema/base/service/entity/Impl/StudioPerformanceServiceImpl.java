package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.StudioPerformanceDao;
import com.kata.cinema.base.models.StudioPerformance;
import com.kata.cinema.base.service.entity.StudioPerformanceService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudioPerformanceServiceImpl extends AbstractServiceImpl<Long, StudioPerformance> implements StudioPerformanceService {

    private final StudioPerformanceDao studioPerformanceDao;

    public StudioPerformanceServiceImpl(StudioPerformanceDao studioPerformanceDao) {
        super(studioPerformanceDao);
        this.studioPerformanceDao = studioPerformanceDao;
    }
}
