package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.NominationDao;

import com.kata.cinema.base.dto.response.NominationResponseDto;
import com.kata.cinema.base.models.AwardCeremonyResult;
import com.kata.cinema.base.models.Nomination;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AwardCeremonyResultService;
import com.kata.cinema.base.service.entity.NominationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NominationServiceImpl extends AbstractServiceImpl<Long, Nomination> implements NominationService {

    private final NominationDao nominationDao;
    private final AwardCeremonyResultService awardCeremonyResultService;


    protected NominationServiceImpl(NominationDao nominationDao, AwardCeremonyResultService awardCeremonyResultService) {
        super(nominationDao);
        this.nominationDao = nominationDao;
        this.awardCeremonyResultService = awardCeremonyResultService;
    }

    @Override
    public Nomination findNomination(Long id) {
        Optional<Nomination> nomination = nominationDao.getById(id);
        if (nomination.isPresent()) {
            return nomination.get();
        } else {
            throw new NullPointerException("Фильм не найден");
        }
    }

    @Override
    public List<NominationResponseDto> toDtoList() {
        return nominationDao.toDtoList();
    }

    @Override
    public void deleteById(Long id) {
        List<AwardCeremonyResult> awardCeremonyResult = awardCeremonyResultService.getAll();
        for (AwardCeremonyResult a: awardCeremonyResult){
            if (a.getNomination().equals(findNomination(id))){
                throw new RuntimeException();
            } else {
                super.deleteById(id);
            }
        }
    }
}
