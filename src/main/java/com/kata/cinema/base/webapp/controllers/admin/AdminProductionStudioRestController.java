package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.ProductionStudioRequestDto;
import com.kata.cinema.base.mappers.ProductionStudioMapper;
import com.kata.cinema.base.service.entity.ProductionStudioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/studios")
@AllArgsConstructor
public class AdminProductionStudioRestController {

    private final ProductionStudioService productionStudioService;
    private final ProductionStudioMapper productionStudioMapper;

    @PostMapping
    public void createProductionStudio(@RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.create(productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
    }

    @DeleteMapping("/{id}")
    public void deleteProductionStudio(@PathVariable("id") Long id) {
        productionStudioService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateProductionStudio(@PathVariable("id") Long id,
                                       @RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.updateById(id, productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
    }
}
