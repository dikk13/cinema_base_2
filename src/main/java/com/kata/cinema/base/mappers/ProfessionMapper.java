package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.Profession;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProfessionMapper {
    ProfessionResponseDto toDTO (Profession profession);

    List<ProfessionResponseDto> toDTOList (List<Profession> list);
    Profession toProfessionFromString (String professionName);
    Profession toProfession (ProfessionResponseDto response);

}
