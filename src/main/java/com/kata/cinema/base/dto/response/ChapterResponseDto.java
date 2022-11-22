package com.kata.cinema.base.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChapterResponseDto implements Serializable {

    private Long id;

    private String name;

}
