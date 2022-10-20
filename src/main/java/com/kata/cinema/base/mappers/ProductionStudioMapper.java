package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.ProductionStudio;
import com.kata.cinema.base.dto.request.ProductionStudioRequestDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductionStudioMapper {

    ProductionStudio productionStudioRequestDtoToProductionStudio(ProductionStudioRequestDto productionStudioRequestDto);

    ProductionStudioRequestDto productionStudioToProductionStudioRequestDto(ProductionStudio productionStudio);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductionStudio updateProductionStudioFromProductionStudioRequestDto(
            ProductionStudioRequestDto productionStudioRequestDto,
            @MappingTarget ProductionStudio productionStudio);
}
