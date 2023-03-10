package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_review_id")
    @SequenceGenerator(name = "seq_review_id", sequenceName = "SEQ_REVIEW_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_moderate")
    private Boolean isModerate = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeReview typeReview;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movies_id")
    @ToString.Include
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id")
    @ToString.Include
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && typeReview == review.typeReview && Objects.equals(title, review.title) && Objects.equals(description, review.description) && Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
