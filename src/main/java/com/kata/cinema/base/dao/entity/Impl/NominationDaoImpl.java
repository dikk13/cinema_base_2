package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.NominationDao;
import com.kata.cinema.base.dto.response.NominationResponseDto;
import com.kata.cinema.base.models.Nomination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NominationDaoImpl extends AbstractDaoImpl<Long, Nomination> implements NominationDao {

    @Override
    public List<NominationResponseDto> toDtoList() {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.response.NominationResponseDto(n.id, n.name) from nomination n", NominationResponseDto.class)
                .getResultList();
    }
}
