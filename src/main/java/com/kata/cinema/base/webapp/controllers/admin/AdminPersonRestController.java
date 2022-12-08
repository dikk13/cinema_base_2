package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.PersonRequestDto;
import com.kata.cinema.base.exception.PersonIsPresentException;
import com.kata.cinema.base.mappers.PersonMapper;
import com.kata.cinema.base.service.entity.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/persons")
public class AdminPersonRestController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<Void> addNewPerson(@RequestBody PersonRequestDto personRequestDto) {
        if (personService.existById(personRequestDto.getId())) {
            throw new PersonIsPresentException("Знаменитость с таким ID уже существует");
        } else {
            personService.create(personMapper.toPerson(personRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@RequestBody PersonRequestDto personRequestDto) {
        personService.update(personMapper.toPerson(personRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
