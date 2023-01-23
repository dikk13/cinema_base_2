package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class CriticalReview {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeReview")
    private TypeReview typeReview;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "critic_id")
    private User critic;

    @Column(name = "score")
    private Integer score;
}
