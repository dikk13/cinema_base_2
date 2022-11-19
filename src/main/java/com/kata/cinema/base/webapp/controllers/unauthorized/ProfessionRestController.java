package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.mappers.ProfessionMapper;
import com.kata.cinema.base.service.entity.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/professions")
public class ProfessionRestController {

    private final ProfessionService professionService;
    private final ProfessionMapper professionMapper;

    @GetMapping
    public ResponseEntity<?> getAllProfessions() {
        List<ProfessionResponseDto> toDTOList = professionMapper.toDTOList(professionService.getAll());
        return new ResponseEntity<>(toDTOList, HttpStatus.OK);
    }
}
