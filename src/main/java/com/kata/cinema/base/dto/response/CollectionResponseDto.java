package com.kata.cinema.base.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CollectionResponseDto {
    private Long id;
    private String name;
    private String previewUrl;
    private Integer countMovies;
    private Integer countViewedMovies;
}
