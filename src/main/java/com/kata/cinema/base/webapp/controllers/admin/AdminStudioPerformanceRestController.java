package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dto.request.StudioPerformanceRequestDto;
import com.kata.cinema.base.dto.response.StudioPerformanceResponseDto;
import com.kata.cinema.base.mappers.StudioPerformanceMapper;
import com.kata.cinema.base.models.StudioPerformance;
import com.kata.cinema.base.service.entity.StudioPerformanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/performance")
@AllArgsConstructor
public class AdminStudioPerformanceRestController {

    private final StudioPerformanceService studioPerformanceService;
    private final StudioPerformanceMapper studioPerformanceMapper;

    @GetMapping
    public ResponseEntity<List<StudioPerformanceResponseDto>> getAllStudioPerformance() {
        return ResponseEntity.ok(studioPerformanceMapper.toDTOList(studioPerformanceService.getAll()));
    }

    @PostMapping
    public ResponseEntity<Void> addNewStudioPerformance(@RequestBody StudioPerformanceRequestDto studioPerformanceRequestDto) {
        studioPerformanceService.create(studioPerformanceMapper.toStudioPerformance(studioPerformanceRequestDto));
        return ResponseEntity.ok(null);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudioPerformanceById(@PathVariable("id") long id) {
        try {
            studioPerformanceService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("вероятно существует связь в ProductionsStudio");
        }
        return ResponseEntity.ok(null);
    }

}
