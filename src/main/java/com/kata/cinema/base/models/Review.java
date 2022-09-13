package com.kata.cinema.base.models;

import com.kata.cinema.base.enums.TypeReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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
    @JoinColumn(name = "movies_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && typeReview == review.typeReview && Objects.equals(title, review.title) && Objects.equals(description, review.description) && Objects.equals(date, review.date) && Objects.equals(movie, review.movie) && Objects.equals(user, review.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeReview, title, description, date, movie, user);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", typeReview=" + typeReview +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", movie=" + movie +
                ", user=" + user +
                '}';
    }
}
