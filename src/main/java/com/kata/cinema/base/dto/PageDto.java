package com.kata.cinema.base.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDto<T> {
   private Long count;

   private List<T> entities;

   public PageDto(Long count, List<T> entities) {
       this.count = count;
       this.entities = entities;
   }
}
