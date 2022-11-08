package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dto.response.GenreResponseDto;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonViewResponseDtoServiceImpl extends AbstractServiceImpl<Long, Person> implements PersonViewResponseDtoService {

    protected PersonViewResponseDtoServiceImpl(@Qualifier("personDaoImpl")AbstractDao<Long, Person> abstractDao) {
        super(abstractDao);
    }

    @Override
    public PersonViewResponseDto getPersonViewResponseDto(Person targetPerson) {
        if (targetPerson != null) {
            return new PersonViewResponseDto(
                    targetPerson.getId(),
                    targetPerson.getHeight(),
                    targetPerson.getDateOfBirth(),
                    targetPerson.getPlaceOfBirth(),
                    targetPerson.getPhotoUrl(),
                    targetPerson.getFirstName(),
                    targetPerson.getOriginalFirstName(),
                    targetPerson.getCountMovie(),
                    targetPerson.getGenres().stream().map(r -> new GenreResponseDto(r.getId(),
                            r.getName())).collect(Collectors.toList()),
                    targetPerson.getProfessions().stream().map(r -> new ProfessionResponseDto(r.getId(),
                            r.getName())).collect(Collectors.toList())
            );
        }
        return null;
    }
}
