package com.kata.cinema.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentDto {
    private Long id;
    private String login;
    private String avatarUrl;

}

