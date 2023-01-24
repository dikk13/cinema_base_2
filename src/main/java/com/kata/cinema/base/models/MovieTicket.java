package com.kata.cinema.base.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movie_tickets")
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieTicket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie_tickets_id")
    @SequenceGenerator(name = "seq_movie_tickets_id", sequenceName = "SEQ_MOVIETICKETS_ID", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private Movie movie;

    @Column
    private LocalDate endShowDate;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTicket that = (MovieTicket) o;
        return Objects.equals(id, that.id) && Objects.equals(endShowDate, that.endShowDate);
    }
}
