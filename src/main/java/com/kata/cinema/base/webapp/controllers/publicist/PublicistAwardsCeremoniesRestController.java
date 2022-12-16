package com.kata.cinema.base.webapp.controllers.publicist;

import com.kata.cinema.base.dto.request.AwardsCeremonyResultRequestDto;
import com.kata.cinema.base.mappers.AwardCeremonyResultMapper;
import com.kata.cinema.base.service.entity.AwardCeremonyResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publicist/awards/ceremonies/")
@AllArgsConstructor
public class PublicistAwardsCeremoniesRestController {

    AwardCeremonyResultService awardCeremonyResultService;
    AwardCeremonyResultMapper awardCeremonyResultMapper;

    @PostMapping("/{id}/result")
    public void addAwardsCeremonyResult(@RequestBody AwardsCeremonyResultRequestDto resultRequestDto) {
        awardCeremonyResultService.create(awardCeremonyResultMapper.toResult(resultRequestDto));
    }

    @DeleteMapping("/result/{id}")
    public void deleteAwardsCeremonyResult(@PathVariable Long id) {
        awardCeremonyResultService.deleteById(id);
    }

}
