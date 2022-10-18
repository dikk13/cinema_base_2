package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.NewsDao;
import com.kata.cinema.base.models.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends AbstractDaoImpl<Long, News> implements NewsDao {
}
