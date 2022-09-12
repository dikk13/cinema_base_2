package com.kata.cinema.base.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
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
public class AwardCeremony {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_awardCeremony_id")
    @SequenceGenerator(name = "seq_awardCeremony_id", sequenceName = "SEQ_AWARDCEREMONY_ID", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String dateEvent;

    @Column
    private String placeEvent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_id")
    private Award awards;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwardCeremony awardCeremony = (AwardCeremony) o;

        return (Objects.equals(id, awardCeremony.id)) && Objects.equals(dateEvent, awardCeremony.dateEvent)
                && Objects.equals(placeEvent, awardCeremony.placeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateEvent, placeEvent);

    }

    @Override
    public String toString() {
        return "AwardCeremony{" +
                "id=" + id +
                ", dateEvent='" + dateEvent + '\'' +
                ", placeEvent='" + placeEvent + '\'' +
                ", awards=" + awards +
                '}';
    }
}
