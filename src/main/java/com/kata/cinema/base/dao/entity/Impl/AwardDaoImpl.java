package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AwardDao;
import com.kata.cinema.base.models.Award;
import org.springframework.stereotype.Repository;

@Repository
public class AwardDaoImpl extends AbstractDaoImpl<Long, Award> implements AwardDao {
}
