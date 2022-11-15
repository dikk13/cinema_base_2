package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.ProductionStudioRequestDto;
import com.kata.cinema.base.mappers.ProductionStudioMapper;
import com.kata.cinema.base.service.entity.ProductionStudioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/studios")
@AllArgsConstructor
public class AdminProductionStudioRestController {

    private final ProductionStudioService productionStudioService;
    private final ProductionStudioMapper productionStudioMapper;

    @PostMapping
    public ResponseEntity<Void> createProductionStudio(@RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.create(productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductionStudio(@PathVariable("id") Long id) {
        productionStudioService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductionStudio(@PathVariable("id") Long id,
                                                       @RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.updateById(id, productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
        return ResponseEntity.ok(null);
    }
}
