package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.models.ProductionStudioMovie;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductionStudioMovieMapper {
    ProductionStudioMovie productionMovieStudioResponseDtoToProductionStudioMovie(ProductionMovieStudioResponseDto productionMovieStudioResponseDto);

    @Mapping(source = "studio.id", target = "id")
    @Mapping(source = "studio.name", target = "name")
    @Mapping(source = "studio.performance.id", target = "performanceId")
    @Mapping(source = "studio.performance.name", target = "performanceName")
    ProductionMovieStudioResponseDto productionStudioMovieToProductionMovieStudioResponseDto(ProductionStudioMovie productionStudioMovie);

}
