package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.Privacy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FolderPersonResponseDto implements Serializable {
    private Long id;
   private String name;
   private String description;
    private Boolean favourites;
    private Privacy privacy;
   private Long countPerson;


}
