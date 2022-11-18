package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.mappers.ExcertionMapper;
import com.kata.cinema.base.models.Excertion;
import com.kata.cinema.base.models.HistoryPerson;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.HistoryType;
import com.kata.cinema.base.service.dto.ExcertionResponseDtoService;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import com.kata.cinema.base.service.entity.ExcertionService;
import com.kata.cinema.base.service.entity.HistoryService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private final PersonService personService;
    private final HistoryService historyService;
    private final ExcertionService excertionService;
    private final ExcertionMapper excertionMapper;
    private final ExcertionResponseDtoService excertionResponseDtoService;
    private final PersonViewResponseDtoService personViewResponseDtoService;

    public PersonRestController(PersonService personService, HistoryService historyService, ExcertionService excertionService,
                                ExcertionMapper excertionMapper,
                                ExcertionResponseDtoService excertionResponseDtoService, PersonViewResponseDtoService personViewResponseDtoService) {
        this.personService = personService;
        this.historyService = historyService;
        this.excertionService = excertionService;
        this.excertionMapper = excertionMapper;
        this.excertionResponseDtoService = excertionResponseDtoService;
        this.personViewResponseDtoService = personViewResponseDtoService;
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
    public ResponseEntity<PageDto<ExcertionResponseDto>> getPersonExcertions(@PathVariable("id") long personId,
                                                                             @PathVariable("pageNumber") Integer pageNumber,
                                                                             @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("personId", personId);
        return ResponseEntity.ok(excertionResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable("id") Long id, @AuthenticationPrincipal User currentUser) {
        PersonViewResponseDto person = personViewResponseDtoService.getPersonViewResponseDto(id);
        if (person == null) {
            return new ResponseEntity<>("There's no such person exist.", HttpStatus.BAD_REQUEST);
        }

        if (currentUser != null) {

            Optional<Person> personHistory = personService.getById(id);
            if (personHistory.isPresent()) {
                HistoryPerson historyPerson = new HistoryPerson();
                historyPerson.setPerson(personHistory.get());
                historyPerson.setDate(LocalDateTime.now());
                historyPerson.setUser(currentUser);
                historyPerson.setType(HistoryType.PERSON);
                historyService.addToHistoryPerson(historyPerson);
            }
        }

        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
