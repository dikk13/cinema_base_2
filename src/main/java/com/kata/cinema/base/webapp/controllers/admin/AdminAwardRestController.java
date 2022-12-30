package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.AwardCeremonyRequestDto;
import com.kata.cinema.base.dto.request.AwardRequestDto;
import com.kata.cinema.base.exception.AwardNotFoundException;
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
    public ResponseEntity<Void> addNewAwardCeremony(@RequestBody AwardCeremonyRequestDto awardCeremonyRequestDto) {
        awardCeremonyService.create(awardCeremonyMapper.toAwardCeremony(awardCeremonyRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAward(@RequestBody AwardRequestDto awardRequestDto) {
        awardService.update(awardMapper.toAward(awardRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/ceremony/{id}")
    public ResponseEntity<Void> updateAwardCeremony(@RequestBody AwardCeremonyRequestDto awardCeremonyRequestDto) {
        awardCeremonyService.update(awardCeremonyMapper.toAwardCeremony(awardCeremonyRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
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
            throw new AwardNotFoundException("Award Ceremony with this ID doesn't exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
