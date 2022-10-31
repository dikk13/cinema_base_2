package com.kata.cinema.base.models;

import javax.persistence.*;

@Entity
@Table(name = "ratingComment")
public class RatingComment {

    private Long Id;

    private TypeRating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;


}
