package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.HistoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = HistoryType.Values.MOVIE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryMovie extends History {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

}
