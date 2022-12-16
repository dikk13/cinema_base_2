package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.ExcertionDao;
import com.kata.cinema.base.models.Excertion;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ExcertionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ExcertionServiceImpl extends AbstractServiceImpl<Long, Excertion> implements ExcertionService {
    private final ExcertionDao excertionDao;

    protected ExcertionServiceImpl(AbstractDao<Long, Excertion> abstractDao, ExcertionDao excertionDao) {
        super(abstractDao);
        this.excertionDao = excertionDao;
    }

    @Transactional
    @Override
    public void updateById(Long id, Excertion excertion) {
        if (this.getById(id).isPresent()) {
            excertion.setId(id);
            excertion.setMovie(this.getById(id).get().getMovie());
            excertion.setPerson(this.getById(id).get().getPerson());
            excertionDao.update(excertion);
        }
    }
}