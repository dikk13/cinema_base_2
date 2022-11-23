package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.TopicDao;
import com.kata.cinema.base.models.Topic;
import org.springframework.stereotype.Repository;

@Repository
public class TopicDaoImpl extends AbstractDaoImpl <Long, Topic> implements TopicDao {
}
