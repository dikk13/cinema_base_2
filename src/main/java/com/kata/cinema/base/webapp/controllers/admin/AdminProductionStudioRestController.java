package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.ProductionStudioRequestDto;
import com.kata.cinema.base.mappers.ProductionStudioMapper;
import com.kata.cinema.base.models.ProductionStudio;
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

//    POST /api/admin/studios принимать будет ProductionStudioRequestDto
    @PostMapping("")
    public void createProductionStudio(@RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        productionStudioService.create(productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto));
    }
//    DELETE /api/admin/studios/{id}
    @DeleteMapping("/{id}")
    public void deleteProductionStudio(@PathVariable("id") Long id) {
        productionStudioService.deleteById(id);
    }

//    PUT /api/admin/studios/{id} принимать будет ProductionStudioRequestDto
    @PutMapping("/{id}")
    public void updateProductionStudio(@PathVariable("id") Long id, @RequestBody ProductionStudioRequestDto productionStudioRequestDto) {
        if (productionStudioService.getById(id).isPresent()) {
            ProductionStudio productionStudio = productionStudioMapper.productionStudioRequestDtoToProductionStudio(productionStudioRequestDto);
            productionStudio.setId(id);
            productionStudioService.update(productionStudio);
        }
    }
}
