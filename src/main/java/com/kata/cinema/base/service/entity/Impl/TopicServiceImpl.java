package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.models.Topic;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.TopicService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TopicServiceImpl extends AbstractServiceImpl<Long, Topic> implements TopicService {

    protected TopicServiceImpl(AbstractDao<Long, Topic> abstractDao) {
        super(abstractDao);
    }
}
