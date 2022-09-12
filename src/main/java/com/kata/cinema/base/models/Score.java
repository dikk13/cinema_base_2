package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_score_id")
    @SequenceGenerator(name = "seq_score_id", sequenceName = "SEQ_SCORE_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "score")
    private int score;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && Objects.equals(id, score1.id) && Objects.equals(movie, score1.movie) && Objects.equals(user, score1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, user, score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", movie=" + movie +
                ", user=" + user +
                ", score=" + score +
                '}';
    }
}
