package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ResultDao;
import com.kata.cinema.base.models.Result;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDaoImpl extends AbstractDaoImpl <Long, Result> implements ResultDao {
}
