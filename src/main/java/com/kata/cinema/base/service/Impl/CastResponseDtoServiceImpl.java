package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.CastResponseDtoDao;
import com.kata.cinema.base.dto.CastResponseDto;
import com.kata.cinema.base.dto.MoviePersonResponseDto;
import com.kata.cinema.base.service.abstracts.CastResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CastResponseDtoServiceImpl implements CastResponseDtoService {

    private final CastResponseDtoDao castResponseDtoDao;

    public CastResponseDtoServiceImpl(CastResponseDtoDao castResponseDtoDao) {
        this.castResponseDtoDao = castResponseDtoDao;
    }

    @Override
    public List<CastResponseDto> getCastResponseDtoListByMovieId(Long id) {
        return castResponseDtoDao.getCastResponseDtoListByMovieId(id);
    }

    @Override
    public List<CastResponseDto> getCastResponseDtoWithMoviePersonDtosListByMovieId(Long id, List<MoviePersonResponseDto> persons) {
        List<CastResponseDto> casts = getCastResponseDtoListByMovieId(id);

        for (CastResponseDto cast : casts) {
            if (cast.getPersons() == null) cast.setPersons(new ArrayList<>());
            for (MoviePersonResponseDto mp : persons) {
                if (mp.getProfessionId().equals(cast.getProfessionId())) cast.getPersons().add(mp);
            }
        }
        return casts;
    }
}
