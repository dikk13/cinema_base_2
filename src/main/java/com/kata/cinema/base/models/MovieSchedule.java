package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.FormatType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "movie_schedule")
@ToString
@Setter
@Getter
@NoArgsConstructor
public class MovieSchedule {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_movie_schedule_id")
    @SequenceGenerator(name = "seq_movie_schedule_id", sequenceName = "SEQ_MOVIESCHEDULE_ID", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_ticket_id")
    @ToString.Exclude
    private MovieTicket movieTicket;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column
    private FormatType formatType;

    @Column
    private LocalDateTime dateTime;

    @Column
    private Integer price;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieSchedule that = (MovieSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(formatType, that.formatType) &&
                Objects.equals(dateTime, that.dateTime) && Objects.equals(price, that.price);
    }
}
