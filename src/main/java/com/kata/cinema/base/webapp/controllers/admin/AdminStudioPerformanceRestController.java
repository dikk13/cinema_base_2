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
    public void addNewStudioPerformance(@RequestBody StudioPerformanceRequestDto studioPerformanceRequestDto) {
        studioPerformanceService.create(studioPerformanceMapper.toStudioPerformance(studioPerformanceRequestDto));
    }

    @PutMapping("/{id}")
    public void updateStudioPerformance(@RequestParam(name = "name") String studioPerformanceName, @PathVariable("id") long id) {
        Optional<StudioPerformance> studioPerformanceContainer = studioPerformanceService.getById(id);
        studioPerformanceContainer.get().setName(studioPerformanceName);
        studioPerformanceService.update(studioPerformanceContainer.get());
    }

    @DeleteMapping("/{id}")
    public void deleteStudioPerformanceById(@PathVariable("id") long id) {
        studioPerformanceService.deleteById(id);
    }

}
