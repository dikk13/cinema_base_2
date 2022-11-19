package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.mappers.ExcertionMapper;
import com.kata.cinema.base.service.entity.ExcertionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/excertions")
public class ExcertionRestController {

    private final ExcertionService excertionService;
    private final ExcertionMapper excertionMapper;

    public ExcertionRestController(ExcertionService excertionService, ExcertionMapper excertionMapper) {
        this.excertionService = excertionService;
        this.excertionMapper = excertionMapper;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcertion(@PathVariable("id") long id) {
        excertionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExcertion(@PathVariable("id") long id,
                                                @RequestBody ExcertionRequestDto excertionRequestDto) {
            excertionService.updateById(id, excertionMapper.toExcertion(excertionRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
