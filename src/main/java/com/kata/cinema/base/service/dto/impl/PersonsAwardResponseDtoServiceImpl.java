package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.PersonsAwardResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardResponseDto;
import com.kata.cinema.base.service.dto.PersonsAwardResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsAwardResponseDtoServiceImpl implements PersonsAwardResponseDtoService {

    private final PersonsAwardResponseDtoDao personsAwardResponseDtoDao;

    public PersonsAwardResponseDtoServiceImpl(PersonsAwardResponseDtoDao personsAwardResponseDtoDao) {
        this.personsAwardResponseDtoDao = personsAwardResponseDtoDao;
    }

    @Override
    public List<AwardResponseDto> getPersonsAwards(Long id) {
        return personsAwardResponseDtoDao.getPersonsAwards(id);
    }

}
