package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.AwardsCeremonyResultResponseDto;
import com.kata.cinema.base.models.enums.NominationStatus;
import com.kata.cinema.base.service.dto.PaginationDtoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/awards")
@AllArgsConstructor
public class AwardsCeremonyRestController {

    PaginationDtoService<AwardsCeremonyResultResponseDto> paginationDtoService;

    @GetMapping("/ceremonies/result?(optional=false)awardId={awardId}&" +
            "(optional=false)dateEvent={dateEvent}&" +
            "(optional=false)nominationStatus={nominationStatus}")
    public PageDto<AwardsCeremonyResultResponseDto> getAwardsCeremonyResult(
            @RequestParam("awardId") Long awardId, @RequestParam("dateEvent") LocalDate dateEvent,
            @RequestParam("nominationStatus") NominationStatus nominationStatus) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("awardId", awardId);
        parameters.put("dateEvent", dateEvent);
        parameters.put("nominationStatus", nominationStatus);
        return paginationDtoService.getPageDtoWithParameters(1, 50, parameters);
    }

}
