package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.models.ProductionStudioMovie;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductionStudioMovieMapper {
    ProductionStudioMovie productionMovieStudioResponseDtoToProductionStudioMovie(ProductionMovieStudioResponseDto productionMovieStudioResponseDto);

    @Mapping(source = "productionStudioMovie.studio.name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "performance", target = "performance")
    ProductionMovieStudioResponseDto productionStudioMovieToProductionMovieStudioResponseDto(ProductionStudioMovie productionStudioMovie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductionStudioMovie updateProductionStudioMovieFromProductionMovieStudioResponseDto(ProductionMovieStudioResponseDto productionMovieStudioResponseDto, @MappingTarget ProductionStudioMovie productionStudioMovie);
}
