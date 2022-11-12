package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMovieResponseDto {

    private Long count;
    private Long countPositive;
    private Long countNegative;
    private Long countNeutral;

    private PageDto<ReviewResponseDto> page;

    public ReviewMovieResponseDto(Long count, Long countPositive, Long countNegative, Long countNeutral) {
        this.count = count;
        this.countPositive = countPositive;
        this.countNegative = countNegative;
        this.countNeutral = countNeutral;
    }
}
