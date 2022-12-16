package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.AwardsCeremonyResultResponseDtoDao;
import com.kata.cinema.base.dto.response.AwardsCeremonyResultResponseDto;


public class AwardsCeremonyResultResponseDtoServiceImpl extends PaginationDtoServiceImpl<AwardsCeremonyResultResponseDto> {

    private final AwardsCeremonyResultResponseDtoDao paginationDtoDao;


    public AwardsCeremonyResultResponseDtoServiceImpl(AwardsCeremonyResultResponseDtoDao awardsCeremonyResultResponseDtoDao) {
        super(awardsCeremonyResultResponseDtoDao);
        this.paginationDtoDao = awardsCeremonyResultResponseDtoDao;
    }

}
