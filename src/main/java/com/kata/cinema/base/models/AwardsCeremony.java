package com.kata.cinema.base.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class AwardsCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_awardsCeremony_id")
    @SequenceGenerator(name="seq_awardsCeremony_id",sequenceName="SEQ_AWARDSCEREMONY_ID", allocationSize=1)
    @Column
    private Long id;

    @Column(unique = true)
    private String dateEvent;

    @Column
    private String placeEvent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_id")
    private Awards awards;




    public AwardsCeremony(Long id, String dateEvent, String placeEvent) {
        this.id = id;
        this.dateEvent = dateEvent;
        this.placeEvent = placeEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardsCeremony awardsCeremony = (AwardsCeremony) o;

        return (Objects.equals(id, awardsCeremony.id)) && Objects.equals(dateEvent, awardsCeremony.dateEvent)
                && Objects.equals(placeEvent, awardsCeremony.placeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateEvent, placeEvent);

    }

    @Override
    public String toString() {
        return "AwardsCeremony{" +
                "id=" + id +
                ", dateEvent='" + dateEvent + '\'' +
                ", placeEvent='" + placeEvent + '\'' +
                ", awards=" + awards +
                '}';
    }
}
