package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.TypeRating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rating_сomment")
@Setter
@Getter
@NoArgsConstructor
public class RatingComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rating_comment_id")
    @SequenceGenerator(name = "seq_rating_comment_id", sequenceName = "SEQ_RATING_COMMENT_ID", allocationSize = 1)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private TypeRating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comments comments;

}
