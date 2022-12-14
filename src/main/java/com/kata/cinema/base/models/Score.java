package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","movie_id"}))
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

    @Column(name = "score")
    private Integer score;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = true, updatable = false)
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(score, score1.score) && Objects.equals(id, score1.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", movie=" + movie +
                ", user=" + user +
                ", score=" + score +
                ", date=" + date +
                '}';
    }
}
