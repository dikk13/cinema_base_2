package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dto.request.MediaFranchiseRequestDto;
import com.kata.cinema.base.exception.FranchiseIsPresentException;
import com.kata.cinema.base.exception.FranchiseNotFoundException;
import com.kata.cinema.base.mappers.MediaFranchiseMapper;
import com.kata.cinema.base.service.entity.MediaFranchiseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/franchises")
public class AdminFranchiseRestController {

    private final MediaFranchiseMapper franchiseMapper;
    private final MediaFranchiseService franchiseService;


    @PostMapping
    public ResponseEntity<Void> addNewFranchise(@RequestBody MediaFranchiseRequestDto franchiseRequestDto,
                                                @RequestParam String name) {
        if (franchiseService.existByName(name)) {
            throw new FranchiseIsPresentException("Franchise with this name is already exist");
        } else {
            franchiseService.create(franchiseMapper.toMediaFranchise(franchiseRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFranchise(@RequestBody MediaFranchiseRequestDto franchiseRequestDto,
                                                @PathVariable("id") Long id,
                                                @RequestParam String name) {
        if (franchiseService.existById(id) || franchiseService.existByName(name)) {
            franchiseService.update(franchiseMapper.toMediaFranchise(franchiseRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new FranchiseNotFoundException("Franchise with this ID or Name doesn't exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable("id") Long id) {
        if (franchiseService.existById(id)) {
            franchiseService.deleteById(id);
        } else {
            throw new FranchiseNotFoundException("Franchise with this ID doesn't exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
