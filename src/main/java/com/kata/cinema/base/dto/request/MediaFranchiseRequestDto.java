package com.kata.cinema.base.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MediaFranchiseRequestDto {

    @NotBlank
    private String name;
}