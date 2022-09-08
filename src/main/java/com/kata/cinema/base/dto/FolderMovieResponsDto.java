package com.kata.cinema.base.dto;

import com.kata.cinema.base.enums.Category;
import com.kata.cinema.base.enums.Privacy;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FolderMovieResponsDto {
    Long id;
    Category category;
    Privacy privacy;
    String name;
    String description;
}
