package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ProfessionDao;
import com.kata.cinema.base.exception.ProfessionNotFountException;
import com.kata.cinema.base.models.Profession;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ProfessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ProfessionServiceImpl extends AbstractServiceImpl<Long, Profession> implements ProfessionService {

    private final ProfessionDao professionDao;

    public ProfessionServiceImpl(ProfessionDao professionDao) {
        super(professionDao);
        this.professionDao = professionDao;
    }

    @Override
    public Optional<Profession> getByName(String profession) {
        return professionDao.getByName(profession);
    }

    @Override
    @Transactional
    public void updateById(Long id, String name) {
        Optional<Profession> optionalProfession = getById(id);
        if (optionalProfession.isPresent()) {
            Profession profession = optionalProfession.get();
            profession.setId(id);
            profession.setName(name);
            professionDao.update(profession);
        } else {
            throw new ProfessionNotFountException("Profession not found.");
        }
    }
}
