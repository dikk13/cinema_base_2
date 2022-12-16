package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.AwardsCeremonyResultResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardsCeremonyResultResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AwardsCeremonyResultResponseDtoServiceImpl extends PaginationDtoServiceImpl<AwardsCeremonyResultResponseDto> {

    public AwardsCeremonyResultResponseDtoServiceImpl(AwardsCeremonyResultResponseDtoDao awardsCeremonyResultResponseDtoDao) {
        super(awardsCeremonyResultResponseDtoDao);
    }

}
