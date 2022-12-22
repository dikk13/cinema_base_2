package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.RedactorStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "redactor_comment")
@Entity
@Setter
@ToString
@Getter
@NoArgsConstructor
public class RedactorComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comments_id")
    @SequenceGenerator(name = "seq_comments_id", sequenceName = "SEQ_COMMENTS_ID", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "review_id", nullable = false, updatable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "news_id", nullable = false, updatable = false)
    private News news;

    private String comment;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    private RedactorStatus status;

}

