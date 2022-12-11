package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FolderMovieResponsDto implements Serializable {
    private Long id;
    private Category category;
    private Privacy privacy;
    private String name;
    private String description;


    }

