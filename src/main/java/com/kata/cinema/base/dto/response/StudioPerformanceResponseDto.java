package com.kata.cinema.base.dto.response;


import com.kata.cinema.base.models.StudioPerformance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudioPerformanceResponseDto {
    private Long id;
    private String name;

    public StudioPerformanceResponseDto(StudioPerformance studioPerformance) {
        id = getId();
        name = getName();
    }
}
