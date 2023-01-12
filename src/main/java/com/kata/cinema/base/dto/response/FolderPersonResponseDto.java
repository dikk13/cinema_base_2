package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.Privacy;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FolderPersonResponseDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Boolean favourites;
    private Privacy privacy;
    private Long countPerson;

    public FolderPersonResponseDto(Long id, String name, String description, Boolean favourites, Privacy privacy,  Long countPerson) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.favourites = favourites;
        this.privacy = privacy;
        this.countPerson = countPerson;
    }
}
