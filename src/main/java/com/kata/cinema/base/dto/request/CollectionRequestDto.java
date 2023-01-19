package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.CollectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionRequestDto {
    private String name;
    private CollectionType type;
}
