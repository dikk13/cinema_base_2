package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.StudioPerformanceRequestDto;
import com.kata.cinema.base.dto.response.StudioPerformanceResponseDto;
import com.kata.cinema.base.models.StudioPerformance;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StudioPerformanceMapper {
    StudioPerformanceResponseDto toDTO(StudioPerformance studioPerformance);

    List<StudioPerformanceResponseDto> toDTOList(List<StudioPerformance> list);

    StudioPerformance toStudioPerformance(StudioPerformanceResponseDto studioPerformanceResponseDto);

    StudioPerformance toStudioPerformance(StudioPerformanceRequestDto studioPerformanceRequestDto);
}
