package com.kata.cinema.base.models;

import com.kata.cinema.base.enums.TypeReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_review_id")
    @SequenceGenerator(name = "seq_review_id", sequenceName = "SEQ_REVIEW_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeReview typeReview;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

}
