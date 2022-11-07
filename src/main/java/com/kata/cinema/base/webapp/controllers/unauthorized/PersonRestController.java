package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.mappers.ExcertionMapper;
import com.kata.cinema.base.models.Excertion;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.dto.ExcertionResponseDtoService;
import com.kata.cinema.base.service.entity.ExcertionService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private final PersonService personService;
    private final ExcertionService excertionService;
    private final ExcertionMapper excertionMapper;
    private final ExcertionResponseDtoService excertionResponseDtoService;

    public PersonRestController(PersonService personService, ExcertionService excertionService,
                                ExcertionMapper excertionMapper,
                                ExcertionResponseDtoService excertionResponseDtoService) {
        this.personService = personService;
        this.excertionService = excertionService;
        this.excertionMapper = excertionMapper;
        this.excertionResponseDtoService = excertionResponseDtoService;
    }

    @PostMapping("/{id}/excertions")
    public ResponseEntity<HttpStatus> createPersonExcertion(@PathVariable("id") long personId,
                                                            @RequestBody ExcertionRequestDto excertionRequestDto) {
        Person person = excertionResponseDtoService.findPersonById(personId);
        Excertion newExcertion = excertionMapper.toExcertion(excertionRequestDto);
        newExcertion.setPerson(person);
        excertionService.create(newExcertion);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/{id}/excertions/page/{pageNumber}")
    public PageDto<ExcertionResponseDto> getPersonExcertions(@PathVariable("id") long personId,
                                                             @PathVariable("pageNumber") Integer pageNumber,
                                                             @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("personId", personId);
        return excertionResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }

    @GetMapping("/{id}")
    public PersonViewResponseDto getPerson(@PathVariable("id") Long id) {
        return new PersonViewResponseDto();
    }
}
