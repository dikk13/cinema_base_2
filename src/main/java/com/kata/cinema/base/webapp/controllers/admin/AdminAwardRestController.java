package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.AwardCeremonyRequestDto;
import com.kata.cinema.base.dto.request.AwardRequestDto;
import com.kata.cinema.base.exception.AwardNotFoundException;
import com.kata.cinema.base.exception.CeremonyNotFoundException;
import com.kata.cinema.base.mappers.AwardCeremonyMapper;
import com.kata.cinema.base.mappers.AwardMapper;
import com.kata.cinema.base.service.entity.AwardCeremonyService;
import com.kata.cinema.base.service.entity.AwardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/awards")
public class AdminAwardRestController {

    private final AwardMapper awardMapper;
    private final AwardCeremonyMapper awardCeremonyMapper;
    private final AwardService awardService;
    private final AwardCeremonyService awardCeremonyService;




    @PostMapping
    public ResponseEntity<Void> addNewAward(@RequestBody AwardRequestDto awardRequestDto) {
            awardService.create(awardMapper.toAward(awardRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/ceremony")
    public ResponseEntity<Void> addNewAwardCeremony(@RequestBody AwardCeremonyRequestDto awardCeremonyRequestDto,
                                                    @PathVariable("id") Long id) {
        if (awardService.existById(id)) {
            awardCeremonyService.create(awardCeremonyMapper.toAwardCeremony(awardCeremonyRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AwardNotFoundException("Award with this ID doesn't exist");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAward(@RequestBody AwardRequestDto awardRequestDto,
                                            @PathVariable("id") Long id) {
        if(awardService.existById(id)) {
            awardService.update(awardMapper.toAward(awardRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AwardNotFoundException("Award with this ID doesn't exist");
        }
    }

    @PutMapping("/{id1}/ceremony/{id2}")
    public ResponseEntity<Void> updateAwardCeremony(@RequestBody AwardCeremonyRequestDto awardCeremonyRequestDto,
                                                    @PathVariable("id1") Long id1,
                                                    @PathVariable("id2") Long id2) {
        if (awardService.existById(id1)
                && awardCeremonyService.existById(id2)
                && awardCeremonyService.getAwardIdFromCeremonyId(id2).equals(id1)) {
            awardCeremonyService.update(awardCeremonyMapper.toAwardCeremony(awardCeremonyRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new CeremonyNotFoundException("Award or Ceremony with this ID doesn't exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAward(@PathVariable ("id") Long id) {
        if (awardService.existById(id)) {
            awardService.deleteById(id);
        } else {
            throw new AwardNotFoundException("Award with this ID doesn't exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/ceremony/{id}")
    public ResponseEntity<Void> deleteAwardCeremony(@PathVariable ("id") Long id) {
        if(awardCeremonyService.existById(id)) {
            awardCeremonyService.deleteById(id);
        } else {
            throw new CeremonyNotFoundException("Award Ceremony with this ID doesn't exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
