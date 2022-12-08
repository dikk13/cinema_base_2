package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.Privacy;

public class FolderPersonRequestDto {
    private String name;
    private String description;
    private Boolean favorites;
    private Privacy privacy;
    private Long countPerson;
}
