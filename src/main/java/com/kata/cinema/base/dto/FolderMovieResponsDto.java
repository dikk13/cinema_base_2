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
    private Long id;
    private Category category;
    private Privacy privacy;
    private String name;
    private String description;
}
