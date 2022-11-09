package com.kata.cinema.base.models;


import com.kata.cinema.base.models.enums.TypeRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class ReactionReview {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reactionReview_id")
    @SequenceGenerator(name = "seq_reactionReview_id", sequenceName = "SEQ_REACTIONREVIEW_ID", allocationSize = 1)
    private Long id;

    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    private TypeRating rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;


}
