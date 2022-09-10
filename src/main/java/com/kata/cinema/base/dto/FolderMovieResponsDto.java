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
    protected Long id;
    protected Category category;
    protected Privacy privacy;
    protected String name;
    protected String description;
}
