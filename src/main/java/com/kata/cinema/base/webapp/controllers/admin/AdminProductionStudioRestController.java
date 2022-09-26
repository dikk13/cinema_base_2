package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.ProductionStudioRequestDto;
import com.kata.cinema.base.mappers.ProductionStudioMapper;
import com.kata.cinema.base.service.abstracts.ProductionStudioService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/studios")
public class AdminProductionStudioRestController {

    private final ProductionStudioService productionStudioService;

    private final ProductionStudioMapper productionStudioMapper;

    public AdminProductionStudioRestController(ProductionStudioService productionStudioService,
                                               ProductionStudioMapper productionStudioMapper) {
        this.productionStudioService = productionStudioService;
        this.productionStudioMapper = productionStudioMapper;
    }

    @PostMapping("")
    public void createProductionStudio(@RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.create(productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
    }

    @DeleteMapping("/{id}")
    public void deleteProductionStudio(@PathVariable("id") Long id) {
        productionStudioService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateProductionStudio(@PathVariable("id") Long id, @RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.updateById(id, productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
    }
}
