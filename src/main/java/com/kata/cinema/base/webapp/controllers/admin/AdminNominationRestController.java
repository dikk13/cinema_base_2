package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.response.NominationResponseDto;
import com.kata.cinema.base.models.Nomination;
import com.kata.cinema.base.service.entity.NominationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/nominations")
public class AdminNominationRestController {

    private final NominationService nominationService;

    public AdminNominationRestController(NominationService nominationService) {
        this.nominationService = nominationService;
    }

    @PostMapping
    public void addNomination(@RequestParam (name = "name") String nominationName){
        Nomination nomination = new Nomination();
        nomination.setName(nominationName);
        nominationService.create(nomination);
    }

    @DeleteMapping("/{id}")
    public void deleteNomination(@PathVariable("id") Long id){
        nominationService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateNomination(@RequestParam (name = "name") String nominationName, @PathVariable("id") Long id) {
        Nomination nomination = nominationService.findNomination(id);
        nomination.setName(nominationName);
        nominationService.update(nomination);
    }

    @GetMapping
    public List <NominationResponseDto> getNominationList(){
        return nominationService.toDtoList();
    }

}
