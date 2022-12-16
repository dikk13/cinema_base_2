package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.AwardsCeremonyResultResponseDto;
import com.kata.cinema.base.service.dto.PaginationDtoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/awards")
@AllArgsConstructor
public class AwardsCeremonyRestController {

    PaginationDtoService<AwardsCeremonyResultResponseDto> paginationDtoService;

    @GetMapping("/ceremonies/result?(optional=false)awardId={awardId}&" +
            "(optional=false)dateEvent={dateEvent}&" +
            "(optional=false)nominationStatus={nominationStatus}")
    public PageDto<AwardsCeremonyResultResponseDto> getAwardsCeremonyResult() {
        return paginationDtoService.getPageDto(1, 50);
    }

}
