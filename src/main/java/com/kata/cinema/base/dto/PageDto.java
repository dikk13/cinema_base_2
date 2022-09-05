package com.kata.cinema.base.dto;

import lombok.AllArgsConstructor;

import java.util.List;

public class PageDto<T> {
    Long count;

    List<T> entities;

    public PageDto(Long count, List<T> entities) {
        this.count = count;
        this.entities = entities;
    }
}
