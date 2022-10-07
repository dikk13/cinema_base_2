package com.kata.cinema.base.dto;


import com.kata.cinema.base.models.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolderResponseDto {
    private Long id;
    private String name;
    private Category category;
    private Integer countMovies;

}