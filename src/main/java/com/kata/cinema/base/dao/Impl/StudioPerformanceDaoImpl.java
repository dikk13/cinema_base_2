package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.StudioPerformanceDao;
import com.kata.cinema.base.dao.entity.Impl.AbstractDaoImpl;
import com.kata.cinema.base.models.StudioPerformance;
import org.springframework.stereotype.Repository;

@Repository
public class StudioPerformanceDaoImpl extends AbstractDaoImpl<Long, StudioPerformance> implements StudioPerformanceDao {
}
